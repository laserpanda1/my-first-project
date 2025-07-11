package sia.tacocloud.JMS;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.jms.Destination;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import sia.tacocloud.TacoOrder;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class MessageConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {


        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", TacoOrder.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }
}
      /*  private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE","WEB");
        return message;
        }
    }*/
