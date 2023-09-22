package kr.fingate.gs.core.beans.mq.recover;

import kr.fingate.gs.core.annotation.MQRecover;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MQRecoverHandler extends RejectAndDontRequeueRecoverer implements ApplicationContextAware {
	private ApplicationContext context;
	private static MQRecoverHandler recoverInstance;

	final static Map<String, Method> recoverMethod = new HashMap<>();

	public MQRecoverHandler() {
		setRecoverMethod();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	// Singleton Object 생성
	public static MQRecoverHandler getInstance() {
		if(recoverInstance == null) {
			synchronized(MQRecoverHandler.class) {
				if(recoverInstance == null) recoverInstance = new MQRecoverHandler();
			}
		}

		return recoverInstance;
	}


	// RabbitRecoverService interface를 구현한 Class와 RabbitRecover Annotation Method를 Map에 생성
	private void setRecoverMethod() {
		try {
			ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
			provider.addIncludeFilter(new AssignableTypeFilter(MQRecoverService.class));

			Set<BeanDefinition> components = provider.findCandidateComponents("kr.fingate.gs");

			for (BeanDefinition component : components)	{
			    Class<?> clazz = Class.forName(component.getBeanClassName());
			    Method[] methods = clazz.getMethods();

			    for (Method method : methods) {
			    	MQRecover anno = method.getDeclaredAnnotation(MQRecover.class);
			    	if (anno != null) 	recoverMethod.put(anno.value(), method);
			    }
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// recover 이벤트 발생시 RabbitRecover Annotation value (Queue name)가 선언된 Method invoke
	@Override
	public void recover(Message message, Throwable cause) {

		try {
			// 큐 오류 처리 필요
			// Log 처리 및 SMS 발송
			Method method = recoverMethod.get(message.getMessageProperties().getConsumerQueue());
			Object service = context.getBean(method.getDeclaringClass());
			method.invoke(service, message, cause);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}