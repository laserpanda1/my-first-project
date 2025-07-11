package sia.tacocloud.JMS;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import sia.tacocloud.TacoOrder;

@Component
public class JmsOrderReceiver implements OrderReceiver{

    private JmsTemplate jmsTemplate;

    public JmsOrderReceiver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public TacoOrder receiveOrder() {
        return (TacoOrder) jmsTemplate.receiveAndConvert("tacocloud.order.queue");
    }
}
