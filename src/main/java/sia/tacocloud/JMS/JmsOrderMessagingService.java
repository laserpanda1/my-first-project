package sia.tacocloud.JMS;

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

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jmsTemplate.send(session -> session.createObjectMessage(order));
    }

}

