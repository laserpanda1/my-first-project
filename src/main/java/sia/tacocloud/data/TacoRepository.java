package sia.tacocloud.data;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloud.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
