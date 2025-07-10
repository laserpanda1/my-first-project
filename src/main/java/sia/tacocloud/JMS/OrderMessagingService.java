package sia.tacocloud.JMS;

import sia.tacocloud.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder tacoOrder);
}
