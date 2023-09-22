package kr.fingate.gs.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional({ConditionalPropertyList.OnCondition.class})
public @interface ConditionalPropertyList {
    public String prefix() default "";
    Class object();

    class OnCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            String prefix = (String) Objects.requireNonNull(metadata.getAllAnnotationAttributes(
                    ConditionalPropertyList.class.getName())).getFirst("prefix");

            Class cl = (Class) Objects.requireNonNull(metadata.getAllAnnotationAttributes(
                    ConditionalPropertyList.class.getName())).getFirst("object");


            List<Object> propertyList = (List<Object>) Binder.get(context.getEnvironment())
                    .bind(prefix, cl)
                    .orElse(new ArrayList<Object>());

            return !propertyList.isEmpty();
        }
    }
}
