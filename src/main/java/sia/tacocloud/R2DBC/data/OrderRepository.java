package sia.tacocloud.R2DBC.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sia.tacocloud.R2DBC.TacoOrder;

public interface OrderRepository extends ReactiveCrudRepository<TacoOrder, Long> {

}
