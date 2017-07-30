package com.dr.galaxy.gamehome.configuration;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.repository.ArticleRepository;
import com.dr.galaxy.gamehome.util.EntityManagerFactoryProvider;

@Configuration
@EnableJpaRepositories(basePackageClasses=ArticleRepository.class)
public class DataConfiguration {
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		driverManagerDataSource.setUrl("jdbc:postgresql:gamehome");
		driverManagerDataSource.setUsername("testuser");
		driverManagerDataSource.setPassword("test123!");
		return driverManagerDataSource;
	}

    /**
     * Based on a DataSource, provides EntityManager (JPA)
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        return EntityManagerFactoryProvider.get(dataSource, Article.class.getPackage().getName());
    }

    /**
     * Based on a EntityManager, provides TransactionManager (JPA)
     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
}
