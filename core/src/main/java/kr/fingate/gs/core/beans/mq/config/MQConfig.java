package kr.fingate.gs.core.beans.mq.config;

import com.rabbitmq.client.ConnectionFactory;
import kr.fingate.gs.core.annotation.ConditionalPropertyList;
import kr.fingate.gs.core.beans.mq.bind.*;
import kr.fingate.gs.core.beans.mq.recover.MQRecoverHandler;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Fingate 안장식
 *
 */

@Configuration
@ConditionalPropertyList(prefix = MQConfig.MQ_HOST_PREFIX, object = MQHostList.class)
public class MQConfig implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private ApplicationContext context;

    static final String MQ_HOST_PREFIX = "server.mq-hosts";
    static final String MQ_SERVER_PREFIX = "server.mq";
    static final String MQ_FACTORY_BEAN = "MQFactoryBean";
    public static final String MQ_LISTENER_BEAN ="MQListenerBean";
    public static final String MQ_TEMPLATE_BEAN = "MQTemplateBean";
    private MQServer mqServer;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.mqServer = MQServer.init(context.getEnvironment(), MQConfig.MQ_SERVER_PREFIX);

        MQHostList hostList = MQHostList.init(context.getEnvironment(), MQConfig.MQ_HOST_PREFIX);

        // primary annotation 설정
        final AtomicBoolean primary = new AtomicBoolean(true);
        for(MQHost host : hostList) {
            // v-host bean 등록
            registerBeans(registry, host, primary.get());
            primary.set(false);
        }
    }

    private void registerBeans(BeanDefinitionRegistry registry, MQHost host, boolean primary) {

        // virtual host name으로 bean 이름을 구성 하기 위해 key로 설정
        String key = host.getVHost();

        // ConnectionFactory bean 생성
        BeanDefinition beanDefinition = createFactoryBean(host, primary);
        String connectionFactoryBeanName = key + MQConfig.MQ_FACTORY_BEAN;
        registry.registerBeanDefinition(connectionFactoryBeanName, beanDefinition);

        CachingConnectionFactory connectionFactory = (CachingConnectionFactory) context.getBean(connectionFactoryBeanName);

        if(host.getListener() != null) {
            // Listener 사용 시 SimpleRabbitListenerContainerFactory bean 생성
            beanDefinition = createListenerBean(host, connectionFactory, primary);
            String listenerContainerFactoryBeanName = key + MQConfig.MQ_LISTENER_BEAN;
            registry.registerBeanDefinition(listenerContainerFactoryBeanName, beanDefinition);
        }

        if(host.getPublisher() != null && host.getPublisher().isEnabled()) {
            // Publisher 사용 시 RabbitTemplate bean 생성
            beanDefinition = createTemplateBean(host, connectionFactory, primary);
            String rabbitTemplateBeanName = key + MQConfig.MQ_TEMPLATE_BEAN;
            registry.registerBeanDefinition(rabbitTemplateBeanName, beanDefinition);
        }
    }

    private BeanDefinition createFactoryBean(final MQHost host, final boolean isPrimary) {
        try {
            ConnectionFactory factory = new ConnectionFactory();

            // AWS amqps SSL 연결을 위해 추가 세팅
            factory.useSslProtocol();
            factory.setUsername(host.getUser());
            factory.setPassword(host.getPass());
            Optional.ofNullable(host.getVHost()).ifPresent(factory::setVirtualHost);
            BeanDefinitionBuilder beanDefinitionBuilder = createBeanDefinitionBase(CachingConnectionFactory.class, isPrimary);

            beanDefinitionBuilder.addConstructorArgValue(factory)
                    .addPropertyValue("addresses", mqServer.getAddresses());

            Publisher publisher = host.getPublisher();
            if(publisher != null && publisher.isEnabled() && publisher.isConfirms()) {
                // Publisher가 메시지 전송시 ConfirmCallback 을 사용
                beanDefinitionBuilder.addPropertyValue("publisherConfirmType", CachingConnectionFactory.ConfirmType.CORRELATED);
            }

            return beanDefinitionBuilder.getBeanDefinition();
        } catch (Exception e) {
//            logger.error(e.getMessage());
            return null;
        }
    }

    private BeanDefinition createListenerBean(final MQHost host, CachingConnectionFactory connectionFactory, boolean isPrimary) {
        BeanDefinitionBuilder beanDefinitionBuilder = createBeanDefinitionBase(SimpleRabbitListenerContainerFactory.class, isPrimary);

        Listener listener = host.getListener();
        if(listener != null) {
            beanDefinitionBuilder
                    .addPropertyValue("connectionFactory", connectionFactory)
                    .addPropertyValue("acknowledgeMode", (listener.getAcknowledgeMode() == null) ? AcknowledgeMode.MANUAL : host.getListener().getAcknowledgeMode())
                    .addPropertyValue("messageConverter", new Jackson2JsonMessageConverter())
                    .addPropertyValue("concurrentConsumers", host.getListener().getConcurrentConsumers());

            Retry retry = (listener != null) ? listener.getRetry() : null;
            if (retry != null && retry.isEnabled()) {
                // Listener 및 recoverer 사용 시 설정
                beanDefinitionBuilder.addPropertyValue("adviceChain", RetryInterceptorBuilder.stateless()
                        .maxAttempts(retry.getMaxAttempts())                // 최대 재시도 횟수
                        .recoverer(MQRecoverHandler.getInstance())        // recover instance 생성, 메시지 읽기 실패 시에 호출됨
                        .backOffOptions(retry.getInitialInterval(), retry.getMultiplier(), retry.getMaxInterval()) //(2,2,10) 2초 간격으로, 2번, 최대 10초 내에 재시도
                        .build());
            }
        }
        return beanDefinitionBuilder.getBeanDefinition();
    }

    private BeanDefinition createTemplateBean(final MQHost host, CachingConnectionFactory connectionFactory, boolean isPrimary) {
        BeanDefinitionBuilder beanDefinitionBuilder = createBeanDefinitionBase(RabbitTemplate.class, isPrimary);

        beanDefinitionBuilder
                .addPropertyValue("connectionFactory", connectionFactory)
                .addPropertyValue("replyTimeout", 10000L) 				// use a property for the reply timeout
                .addPropertyValue("messageConverter", new Jackson2JsonMessageConverter());

        if(host.getPublisher().isEnabled() && host.getPublisher().isConfirms()) {
            // Publisher가 메시지 전송시 ConfirmCallback 을 사용시 설정
            beanDefinitionBuilder.addPropertyValue("mandatory", true);
        }

        return beanDefinitionBuilder.getBeanDefinition();
    }


    private BeanDefinitionBuilder createBeanDefinitionBase(final Class type, final boolean isPrimary) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(type);

        if (isPrimary) {
            beanDefinitionBuilder.getBeanDefinition().addQualifier(new AutowireCandidateQualifier(Primary.class));
            beanDefinitionBuilder.getBeanDefinition().setPrimary(true);
        }

        return beanDefinitionBuilder;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
