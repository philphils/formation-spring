package org.formation.spring.core.conf.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CacheDatabaseConfiguredCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return "cache".equalsIgnoreCase(context.getEnvironment().getProperty("fr.insee.formation-spring.database.pool"));
	}
}
