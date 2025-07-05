package sia.tacocloud;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import sia.tacocloud.Ingredient.Type;
import sia.tacocloud.data.IngredientRepository;
import sia.tacocloud.data.TacoRepository;
import sia.tacocloud.security.User;
import sia.tacocloud.security.UserRepository;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private TacoRepository tacoRepo;
    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for(Type type: types) {
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));
        }
    }


    @ModelAttribute(name = "order")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public String user(Principal principal) {
        //String username = principal.getName();
        //User user = userRepo.findByUsername(username);
        return principal.getName();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(
                @Valid Taco taco, Errors errors,
                @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco : {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x->x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
