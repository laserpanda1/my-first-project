/*package sia.tacocloud;

import java.util.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Ingredient.Type;
import sia.tacocloud.data.IngredientRepository;

@Configuration
public class DataLoaderConfig {

    @Bean
    public ApplicationRunner dataLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        };
    }
}
*/