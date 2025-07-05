package sia.tacocloud.data;


import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
