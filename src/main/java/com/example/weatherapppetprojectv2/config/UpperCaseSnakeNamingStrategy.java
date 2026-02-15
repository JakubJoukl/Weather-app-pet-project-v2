package com.example.weatherapppetprojectv2.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class UpperCaseSnakeNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        if (name == null) return null;
        String upperCaseSnakeCase = name.getText()
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toUpperCase();
        return Identifier.toIdentifier(upperCaseSnakeCase);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return toPhysicalColumnName(name, context);
    }
}