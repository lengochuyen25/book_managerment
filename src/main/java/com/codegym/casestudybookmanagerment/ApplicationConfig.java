package com.codegym.casestudybookmanagerment;

import com.codegym.casestudybookmanagerment.formatter.AuthorFormatter;
import com.codegym.casestudybookmanagerment.service.AuthorService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableTransactionManagement
@ComponentScan("com.codegym")
public class ApplicationConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    @Autowired
    Environment env;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new AuthorFormatter(applicationContext.getBean(AuthorService.class)));
    }



    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(">>> config resource image for show");
//        String fileUpload = env.getProperty("file_upload").toString();

        // Image resource.
        registry.addResourceHandler("/image/**") //
                .addResourceLocations("file:/home/min2208/Desktop/Images Web/");
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");

    }

}