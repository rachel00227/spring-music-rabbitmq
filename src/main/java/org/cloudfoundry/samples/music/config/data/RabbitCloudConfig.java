package org.cloudfoundry.samples.music.config.data;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitCloudConfig extends AbstractCloudConfig {
	
	@Bean
	public ConnectionFactory rabbitFactory() {
	    return connectionFactory().rabbitConnectionFactory();
	}
    
    @Bean
    public Queue simpleQueue() {
        return new Queue("messages", true);
    }
    
    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory rabbitFactory) {
        return new RabbitAdmin(rabbitFactory);
    }
	
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory rabbitFactory){
		RabbitTemplate template = new RabbitTemplate(rabbitFactory);
		template.setQueue("messages");
		return template;
	}

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }
}
