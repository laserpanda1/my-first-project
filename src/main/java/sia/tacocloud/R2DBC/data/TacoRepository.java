package sia.tacocloud.R2DBC.data;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sia.tacocloud.R2DBC.Taco;

public interface TacoRepository extends ReactiveCrudRepository<Taco,Long> {
}
