package kr.fingate.gs.core.beans.db.config;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class MapperNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(final BeanDefinition definition, final BeanDefinitionRegistry registry) {
        return ((AnnotatedBeanDefinition)definition).getMetadata().getClassName();
    }
}
