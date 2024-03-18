package com.alitofigh.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * Created by A_Tofigh at 3/15/2024
 */

@Configuration
public class JpaConfig {

    @Value("${oracle.datasource.url}")
    String oracleUrl;
    @Value("${oracle.username}")
    String oracleUsername;
    @Value("${oracle.password}")
    String oraclePassword;

    @Value("${mysql.datasource.url}")
    String mysqlUrl;
    @Value("${mysql.username}")
    String mysqlUsername;
    @Value("${mysql.password}")
    String mysqlPassword;


    @Bean
    @Profile("oracle")
    public DataSource oracleDatasource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        //builder.driverClassName("");
        builder.url(oracleUrl);
        builder.username(oracleUsername);
        builder.password(oraclePassword);
        return builder.build();
    }

    @Bean
    @Profile("mysql")
    public DataSource mysqlDatasource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url(mysqlUrl);
        builder.username(mysqlUsername);
        builder.password(mysqlPassword);
        return builder.build();
    }
}
