package kr.fingate.gs.core.beans.db.config;

//import com.zaxxer.hikari.HikariDataSource;

import com.zaxxer.hikari.HikariDataSource;
import kr.fingate.gs.core.annotation.ConditionalPropertyList;
import kr.fingate.gs.core.beans.db.bind.DataSourceProperty;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Map;
import java.util.Properties;

/**
 * MyBatis Mapper Auto Configuration 설정
 *
 **/
@Configuration
@ConditionalPropertyList(prefix = DBConfig.PROPERTY_DB_LIST_PREFIX, object = DataSourceList.class)
public class DBConfig implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

	public enum BEAN_TYPE {
		DATASOURCE("DataSource"),
		SESSION_FACTORY("SessionFactory"),
		SESSION_TEMPLATE("SessionTemplate"),
		TRANSACTION_MANAGER(""),
		TRANSACTION_ADVICE("TxAdvice"),
		MAPPER_SCANNER("MapperScanner");

		private final String suffix;

		BEAN_TYPE(String suffix) {
			this.suffix = suffix;
		}

		public String getSuffix() {
			return this.suffix;
		}
	}

	private ApplicationContext context;


	static final String PROPERTY_DB_LIST_PREFIX = "datasources";
	static final String CONFIG_LOCALTION = "classpath:mybatis-config.xml";
	static final String MAPPER_LOCALTION = "/mapper/**/*.xml";

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		DataSourceList datasourceList = DataSourceList.init(context.getEnvironment(), DBConfig.PROPERTY_DB_LIST_PREFIX);

		for(DataSourceProperty datasource : datasourceList) {
			registerBeans(registry, datasource);
		}
	}

	private void registerBeans(BeanDefinitionRegistry registry, DataSourceProperty datasource) {
		// DataSource 등록
		registerDataSource(registry, datasource);

		// SqlSessionFactory
		registerSqlSessionFactory(registry, datasource);

		// SqlSessionTemplate
		registerSqlSessionTemplate(registry, datasource);

		// TransactionManager
		registerTransactionManager(registry, datasource);

		if(datasource.getDataSource().get("jdbcUrl").contains("replication")) {
			AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
			pointcut.setExpression("execution(* " + datasource.getAopExecution() +")");
			registerTransactionAdvice(registry, datasource, pointcut);
		}
		// MapperScannerConfigurer
		registerMapperScanner(registry, datasource);
	}

	private void registerMapperScanner(BeanDefinitionRegistry registry, DataSourceProperty datasource) {
		AbstractBeanDefinition mapperScanner = BeanDefinitionBuilder
				.genericBeanDefinition(MapperScannerConfigurer.class)
				.addPropertyValue("sqlSessionFactoryBeanName", datasource.getName(DBConfig.BEAN_TYPE.SESSION_FACTORY))
				.addPropertyValue("basePackage", datasource.getBasePackage())
				.addPropertyValue("nameGenerator", new MapperNameGenerator())
				.getBeanDefinition();

		registry.registerBeanDefinition(datasource.getName(DBConfig.BEAN_TYPE.MAPPER_SCANNER), mapperScanner);
	}


	private void registerDataSource(BeanDefinitionRegistry registry, DataSourceProperty datasource) {
		Map<String, String> dataSource = datasource.getDataSource();

		GenericBeanDefinition dataSourceBeanDefinition = new GenericBeanDefinition();
		dataSourceBeanDefinition.setBeanClass(HikariDataSource.class);
		dataSourceBeanDefinition.setPropertyValues(new MutablePropertyValues(dataSource));

		registry.registerBeanDefinition(datasource.getName(DBConfig.BEAN_TYPE.DATASOURCE), dataSourceBeanDefinition);
	}

	private void registerSqlSessionFactory(BeanDefinitionRegistry registry, DataSourceProperty datasource) {

		String baseMapper = StringUtils.isEmpty(datasource.getBaseMapper()) ? MAPPER_LOCALTION : datasource.getBaseMapper();

		AbstractBeanDefinition sqlSessionFactory = BeanDefinitionBuilder
				.genericBeanDefinition(SqlSessionFactoryBean.class)
				.addPropertyReference("dataSource", datasource.getName(DBConfig.BEAN_TYPE.DATASOURCE))
				.addPropertyValue("configLocation", CONFIG_LOCALTION)
				.addPropertyValue("mapperLocations", "classpath:" + baseMapper)
				.getBeanDefinition();

		registry.registerBeanDefinition(datasource.getName(DBConfig.BEAN_TYPE.SESSION_FACTORY), sqlSessionFactory);

	}


	private void registerSqlSessionTemplate(BeanDefinitionRegistry registry, DataSourceProperty datasource) {
		AbstractBeanDefinition sqlSessionTemplate = BeanDefinitionBuilder
				.genericBeanDefinition(SqlSessionTemplate.class)
				.addConstructorArgReference(datasource.getName(DBConfig.BEAN_TYPE.SESSION_FACTORY))
				.getBeanDefinition();

		registry.registerBeanDefinition(datasource.getName(DBConfig.BEAN_TYPE.SESSION_TEMPLATE), sqlSessionTemplate);
	}

	private void registerTransactionManager(BeanDefinitionRegistry registry, DataSourceProperty datasource) {
		AbstractBeanDefinition transactionManager = BeanDefinitionBuilder
				.genericBeanDefinition(DataSourceTransactionManager.class)
				.addPropertyReference("dataSource", datasource.getName(DBConfig.BEAN_TYPE.DATASOURCE))
				.addPropertyValue("nestedTransactionAllowed", true)
				.getBeanDefinition();

		registry.registerBeanDefinition(datasource.getName(DBConfig.BEAN_TYPE.TRANSACTION_MANAGER), transactionManager);
	}

	private void registerTransactionAdvice(BeanDefinitionRegistry registry, DataSourceProperty datasource, AspectJExpressionPointcut pointcut) {
		DefaultTransactionAttribute readOnlyAttr = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
		readOnlyAttr.setReadOnly(true);

		String readOnlyTx = readOnlyAttr.toString();

		Properties txAttributes = new Properties();
		txAttributes.setProperty("get*", readOnlyTx);
		txAttributes.setProperty("down*", readOnlyTx);

		TransactionInterceptor txAdvice = new TransactionInterceptor();
		txAdvice.setTransactionAttributes(txAttributes);
		PlatformTransactionManager txManager = (PlatformTransactionManager) context.getBean(datasource.getName(DBConfig.BEAN_TYPE.TRANSACTION_MANAGER));
		txAdvice.setTransactionManager(txManager);
		AbstractBeanDefinition transactionAdvice = BeanDefinitionBuilder
				.genericBeanDefinition(DefaultPointcutAdvisor.class)
				.addPropertyValue("pointcut", pointcut)
				.addPropertyValue("advice", txAdvice)
				.getBeanDefinition();

		registry.registerBeanDefinition(datasource.getName(DBConfig.BEAN_TYPE.TRANSACTION_ADVICE), transactionAdvice);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}



}