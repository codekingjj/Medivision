package com.medivision.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.medivision.medivision",
        entityManagerFactoryRef = "testEntityManager",
        transactionManagerRef = "testTransactionManager"
)
@EnableTransactionManagement
public class MedivisionDataSourceConfig {
    @Value("${spring.datasource.test.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.test.url}")
    private String url;

    @Value("${spring.datasource.test.username}")
    private String username;

    @Value("${spring.datasource.test.password}")
    private String password;

    @Bean(name = "testDatasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Primary
    @Bean(name = "testEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.medivision.medivision");
        factory.setDataSource(dataSource());

        Map<String, Object> properties = new HashMap<>();
        properties.put("generate-ddl", false);
        properties.put("show-sql", true);
        factory.setJpaPropertyMap(properties);

        return factory;
    }

    @Primary
    @Bean(name = "testTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

}
