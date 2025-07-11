package sia.tacocloud.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sia.tacocloud.TacoOrder;
import sia.tacocloud.UI.KitchenUI;

@Slf4j
@Component
public class KafkaOrderListener {

    private KitchenUI ui;

    @Autowired
    public KafkaOrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics="tacocloud-orders-topic")
    public void handle(
            TacoOrder order, ConsumerRecord<String, TacoOrder> record) {
        log.info("Received from partition {} with timestamp {}",
                record.partition(), record.timestamp());

        ui.displayOrder(order);
    }

}
