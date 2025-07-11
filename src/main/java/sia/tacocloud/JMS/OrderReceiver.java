package sia.tacocloud.JMS;

import sia.tacocloud.TacoOrder;

public interface OrderReceiver {
    TacoOrder receiveOrder();
}
