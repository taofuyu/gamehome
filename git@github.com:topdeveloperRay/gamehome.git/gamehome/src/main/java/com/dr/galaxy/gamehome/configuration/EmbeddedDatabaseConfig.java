/*
package com.dr.galaxy.gamehome.configuration;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.dr.galaxy.gamehome.model.Article;
import com.dr.galaxy.gamehome.repository.ArticleRepository;
import com.dr.galaxy.gamehome.util.EntityManagerFactoryProvider;

@Configuration
@EnableJpaRepositories(basePackageClasses=ArticleRepository.class)
public class EmbeddedDatabaseConfig extends AbstractCloudConfig{

	@Bean
	public DataSource dataSource(){
		List<String> dataSourceNames = Arrays.asList("BasicDbcpPooledDataSourceCreator",
                "TomcatJdbcPooledDataSourceCreator", "HikariCpPooledDataSourceCreator",
                "TomcatDbcpPooledDataSourceCreator");
        DataSourceConfig dbConfig = new DataSourceConfig(dataSourceNames);
        return connectionFactory().dataSource(dbConfig);
	}
	
	@Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        return EntityManagerFactoryProvider.get(dataSource, Article.class.getPackage().getName());
    }
	
//	@Bean(name = "transactionManager")
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//		return new JpaTransactionManager(entityManagerFactory);
//	}
}

*/
