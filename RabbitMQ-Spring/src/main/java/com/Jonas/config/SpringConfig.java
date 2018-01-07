package com.Jonas.config;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import com.Jonas.touser.SyncToUserMessageConfiguration;

@Configuration
@ComponentScan("com.Jonas")
@PropertySource("file:config/application.properties")
public class SpringConfig {
	
	
	@Value("${jonas.open.msg.center.rabbitmq.host}")
    private String rabbitmqHost;
	
    @Value("${jonas.open.msg.center.rabbitmq.username}")
    private String rabbitmqUsername;
    
    @Value("${jonas.open.msg.center.rabbitmq.password}")
    private String rabbitmqPassword;
	
    @Autowired
    private SyncToUserMessageConfiguration syncToUserMessageConfiguration;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {  
        return new PropertySourcesPlaceholderConfigurer();  
    }  	
    
	
    @Bean(name = "rabbitConnectionFactory")
    public CachingConnectionFactory CachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory=new  CachingConnectionFactory()  ;
        cachingConnectionFactory.setHost(rabbitmqHost);
        cachingConnectionFactory.setPassword(rabbitmqUsername);
        cachingConnectionFactory.setUsername(rabbitmqPassword);
        cachingConnectionFactory.setChannelCacheSize(30);
        return cachingConnectionFactory;
    }
    
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(CachingConnectionFactory());
        container.setQueueNames("syncToUserMessageMQ_0003e2ad-b3fb-47d6-bab6-1375166eb942123");
        container.setMessageListener(syncToUserMessageConfiguration.syncToUserMessageConsumerListener());
        return container;
    }

    
 
    

}
