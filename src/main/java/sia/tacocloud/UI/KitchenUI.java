package sia.tacocloud.UI;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sia.tacocloud.TacoOrder;

@Component
@Slf4j
public class KitchenUI {

    public void displayOrder(TacoOrder order) {
        log.info("RECEIVED ORDER : " + order);
    }
}
