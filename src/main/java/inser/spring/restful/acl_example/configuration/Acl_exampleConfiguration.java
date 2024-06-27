package inser.spring.restful.acl_example.configuration;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:re/acl_example.properties" })
@EnableJpaRepositories(
        basePackages = Acl_exampleConfiguration.k_repository_package,
        entityManagerFactoryRef = "create_acl_exampleEntityManager",
        transactionManagerRef = "create_acl_exampleTransactionManager"
)
public class Acl_exampleConfiguration {
    public static final String k_spring_jpa = "spring.jpa";
    public static final String k_spring_datasource = "spring.datasource";
    public static final String k_create_acl_exampleDataSource = "create_acl_exampleDataSource";
    public static final String k_entity_package = "inser.spring.restful.acl_example.entity";
    public static final String k_repository_package = "inser.spring.restful.acl_example.repository";

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = k_spring_jpa)
    protected Map<String, String> _get_spring_jpa() {
        return new HashMap<>();
    }

    @Bean
    public Map<String, String> get_spring_jpa() {
        Map<String, String> retorno = new HashMap<>();
        Map<String, String> map = _get_spring_jpa();
        for (var item: map.entrySet()) {
            retorno.put("spring.jpa" + "." + item.getKey(), item.getValue());
        }
        return retorno;
    }

    @Bean
    @ConfigurationProperties(k_spring_datasource)
    public DataSourceProperties get_spring_datasource() {
        return new DataSourceProperties();
    }

    public Acl_exampleConfiguration() {
        super();
    }
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean create_acl_exampleEntityManager() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(create_acl_exampleDataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan(
                new String[] { k_entity_package });
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        Map<String, String> properties = new HashMap<>();
        properties = get_spring_jpa();
        String tex;
        tex = env.getProperty("spring.jpa.hibernate.ddl-auto");
        properties.put("spring.jpa.hibernate.ddl-auto", tex);
        tex = env.getProperty("spring.jpa.generate-ddl");
        properties.put("spring.jpa.generate-ddl", tex);
        tex = env.getProperty("spring.jpa.generate-ddl");
        properties.put("spring.jpa.generate-ddl", tex);
        tex = env.getProperty("spring.jpa.show-sql");
        properties.put("spring.jpa.show-sql", tex);
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(properties);
        return localContainerEntityManagerFactoryBean;
    }

    @Primary
    @Bean(name = k_create_acl_exampleDataSource)
    public DataSource create_acl_exampleDataSource() {
// Other option
//        DriverManagerDataSource dataSource
//                = new DriverManagerDataSource();
//        String tex;
//        tex = env.getProperty("spring.datasource.driver-class-name");
//        dataSource.setDriverClassName(tex);
//        tex = env.getProperty("spring.datasource.url");
//        dataSource.setUrl(tex);
//        tex = env.getProperty("spring.datasource.username");
//        dataSource.setUsername(tex);
//        tex = env.getProperty("spring.datasource.password");
//        dataSource.setPassword(tex);
//        return dataSource;
        DataSourceProperties dataSourceProperties;
        dataSourceProperties = get_spring_datasource();
        return dataSourceProperties.initializeDataSourceBuilder()
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager create_acl_exampleTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                create_acl_exampleEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    public JdbcTemplate todosJdbcTemplate(@Qualifier(k_create_acl_exampleDataSource) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}