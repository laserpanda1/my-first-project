package sia.tacocloud.restclient;

//disorderlysoul <--------

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sia.tacocloud.Ingredient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TacoCloudClient {
    private RestTemplate rest;


    public TacoCloudClient(RestTemplate rest) {
        this.rest = rest;

    }

    public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
    }


/*  (--- Map<String,String> ---)
 public Ingredient getIngredientById(String ingredientId) {
    Map<String,String> urll = new HashMap<>();
    urll.put("id", ingredientId);
    return rest.getForObject("http://localhost:8080/ingredients/{id}",Ingredient.class, ingredientId);
    }
*/

    /*(--- Use getForEntity() ---)

    public Ingredient getIngredientById(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity = rest.getForEntity("http://localhost:8080/ingredients/{id}",Ingredient.class, ingredientId);
        log.info("Fetched time: {}", responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }
    */

    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient.class);
    }

    /*(--- postForEntity() ---)
    public Ingredient createIngredient(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity =
                rest.postForEntity("http://localhost:8080/ingredients", ingredient, Ingredient.class);
        log.info("New resource created at {} ", responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

     */
}
