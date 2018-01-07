package com.Jonas.common;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public abstract class AbstractAMQPConfiguration {

		
	    /**
	     * Create a bean definition for RabbitTemplate.  Since there are several properties
	     * one may want to set after creating a RabbitTemplate from a ConnectionFactory, this
	     * abstract method is provided to allow for that flexibility as compared to
	     * automatically creating a RabbitTemplate by specifying a ConnectionFactory.
	     */
	    @Bean 
	    public abstract RabbitTemplate rabbitTemplate();

	    @Bean
	    public AmqpAdmin amqpAdmin() {
	        return new RabbitAdmin(rabbitTemplate().getConnectionFactory());
	    }
	    
	    /**
	     * Provides convenient access to the default exchange which is always declared on the broker.
	     */
	    public DirectExchange defaultExchange() {
	        return new DirectExchange("");
	    }
}
