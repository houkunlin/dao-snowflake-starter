package com.houkunlin.snowflake.starter.jpa;

import org.hibernate.jpa.AvailableSettings;
import org.hibernate.jpa.spi.IdentifierGeneratorStrategyProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 把 Snowflake 主键生成策略加入到 Hibernate 的默认策略列表中。
 * 使其可以像以下方式使用：
 *
 * <p>@javax.persistence.Id</p>
 * <p>@javax.persistence.GeneratedValue(generator = "id")</p>
 * <p>@org.hibernate.annotations.GenericGenerator(name = "id", strategy = "snowflake")</p>
 * <p>private String id;</p>
 *
 * @author HouKunLin
 */
@Component
public class SnowflakeHibernatePropertiesCustomizer implements HibernatePropertiesCustomizer {

    private final Map<String, Class<?>> map = new HashMap<>();

    public SnowflakeHibernatePropertiesCustomizer() {
        map.put("snowflake", SnowflakeIdentifierGenerator.class);
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(
                AvailableSettings.IDENTIFIER_GENERATOR_STRATEGY_PROVIDER,
                (IdentifierGeneratorStrategyProvider) () -> map);
    }
}