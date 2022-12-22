package com.donfack.DBconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "tetiaryEntityManagerFactory",
        transactionManagerRef = "tetiaryTransactionManager",
        basePackages = {"com.donfack.user.repository"})
public class TetiaryDBConfig {

    @Bean(name="tetiaryDataSource")
    @ConfigurationProperties(prefix="spring.tetiarydatasource")
    public DataSource tetiaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tetiaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tetiaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                @Qualifier("tetiaryDataSource") DataSource tetiaryDataSource) {
        return builder
                .dataSource(tetiaryDataSource)
                .packages("com.donfack.user.model")
                .build();
    }

    @Bean(name = "tetiaryTransactionManager")
    public PlatformTransactionManager tetiaryTransactionManager(
            @Qualifier("tetiaryEntityManagerFactory") EntityManagerFactory tetiaryEntityManagerFactory) {
        return new JpaTransactionManager(tetiaryEntityManagerFactory);
    }



   /* @Value("${spring.secondary.datasource.url}")
    private String url;

    @Value("${spring.secondary.datasource.username}")
    private String username;

    @Value("${spring.secondary.datasource.password}")
    private String password;


    @Bean(name = "secondaryDbDataSource")
    public DataSource secondaryDbDataSource(){
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
            secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                  @Qualifier("secondaryDbDataSource")DataSource secondaryDataSource){
        return builder
                .dataSource(secondaryDataSource)
                .packages("com.donfack.employee.model")
                .build();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory
                    secondaryEntityManagerFactory){
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }*/




























}
