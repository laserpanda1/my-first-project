package sia.tacocloud.RabbitMQ;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sia.tacocloud.JMS.OrderReceiver;
import sia.tacocloud.TacoOrder;
import sia.tacocloud.UI.KitchenUI;

@Component
public class RabbitOrderListener {

    private KitchenUI ui;

    @Autowired
    public RabbitOrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder order) {
        ui.displayOrder(order);
    }

}
*/