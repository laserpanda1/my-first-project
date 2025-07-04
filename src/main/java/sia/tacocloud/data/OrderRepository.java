package sia.tacocloud.data;

import org.springframework.data.domain.Pageable;
import sia.tacocloud.TacoOrder;
import java.util.*;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.security.User;
import java.util.List;



public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
