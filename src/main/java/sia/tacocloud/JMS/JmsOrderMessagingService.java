package sia.tacocloud.JMS;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import sia.tacocloud.TacoOrder;

@Service
public class JmsOrderMessagingService implements  OrderMessagingService{

    private JmsTemplate jmsTemplate;
    private Destination orderQueue;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate, Destination orderQueue) {
        this.jmsTemplate = jmsTemplate;
        this.orderQueue = orderQueue;
    }

    /*(-- convertAndSend --)*/
    @Override
    public void sendOrder(TacoOrder order) {
        jmsTemplate.convertAndSend("taco.order.queue", order);
    }

 /* (-- send --)
    @Override
    public void sendOrder(TacoOrder order) {
        jmsTemplate.send(orderQueue, session -> session.createObjectMessage(order));
    }
*/
}

