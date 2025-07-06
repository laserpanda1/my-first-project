package sia.tacocloud;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.annotation.RestResource;
import sia.tacocloud.data.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Persistable;
import java.util.*;
import java.util.UUID;
import jakarta.persistence.*;

@Data
@Entity
@RestResource(rel="tacos", path="tacos")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToOne(optional = true)
    @JoinColumn(name = "taco_order_id", nullable = true)
    @JsonIgnore
    private TacoOrder tacoOrder;

    @Size(min=1, message = "You must choose at least 1 ingredient")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients ;

   // public void addIngredient(Ingredient ingredient) {
    //    this.ingredients.add(ingredient);
   // }

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

}
