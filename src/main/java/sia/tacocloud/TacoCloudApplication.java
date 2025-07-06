package sia.tacocloud;

import java.util.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.Traverson;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sia.tacocloud.data.IngredientRepository;
import sia.tacocloud.data.OrderRepository;
import sia.tacocloud.data.TacoRepository;
import sia.tacocloud.security.User;
import sia.tacocloud.security.UserRepository;
import sia.tacocloud.Ingredient.Type;

@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");

	}

	@Bean
	public CommandLineRunner dataLoader(
			IngredientRepository repo,
			PasswordEncoder encoder,
			UserRepository userRepo,
			TacoRepository tacoRepo,
			OrderRepository orderRepo  // Добавляем репозиторий заказов
	) {
		return args -> {
			tacoRepo.deleteAll();
			orderRepo.deleteAll();;
			repo.deleteAll();

			// Сохраняем ингредиенты (как у вас)
			Ingredient flourTortilla = new Ingredient("FLTO","Flour Tortilla", Type.WRAP);
			Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
			Ingredient groundBeef = new Ingredient("GRBF","Ground Beef", Type.PROTEIN);
			Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
			Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
			Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
			Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
			Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
			Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
			Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
			repo.save(flourTortilla);
			repo.save(cornTortilla);
			repo.save(groundBeef);
			repo.save(carnitas);
			repo.save(tomatoes);
			repo.save(lettuce);
			repo.save(cheddar);
			repo.save(jack);
			repo.save(salsa);
			repo.save(sourCream);

			// Создаем заказ
			TacoOrder order = new TacoOrder();
			order.setDeliveryName("Test Order");
			order.setDeliveryStreet("123 Main St");
			order.setDeliveryCity("New York");
			order.setDeliveryState("NY");
			order.setDeliveryZip("10001");
			order.setCcNumber("4111111111111111");
			order.setCcExpiration("12/25");
			order.setCcCVV("123");
			TacoOrder savedOrder = orderRepo.save(order);


			// Создаем тако и добавляем в заказ
			Taco taco1 = new Taco();
			taco1.setName("Carnivore");
			taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
			taco1.setTacoOrder(savedOrder);
			order.addTaco(taco1);  // Устанавливаем связь!
			tacoRepo.save(taco1);

			Taco taco2 = new Taco();
			taco2.setName("Bovine Bounty");
			taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
			taco2.setTacoOrder(savedOrder);
			order.addTaco(taco2);  // Добавляем второе тако
			tacoRepo.save(taco2);

			Taco taco3 = new Taco();
			taco3.setName("Veg-Out");
			taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
			taco3.setTacoOrder(savedOrder);
			order.addTaco(taco3);  // Добавляем третье тако
			tacoRepo.save(taco3);

			// Сохраняем заказ (каскадно сохранит тако)
			//orderRepo.save(order);

			// Создаем пользователя
			userRepo.save(new User(
					"habuma",
					encoder.encode("password"),
					"Craig Walls",
					"123 North Street",
					"Cross Roads",
					"TX",
					"76227",
					"123-123-1234"
			));
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
	/*@Bean
	@Profile({"dev","qa"})
	public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepo, PasswordEncoder encoder) {
	}
}
*/ //Пример работы с профилями , этот код активен если активны профили dev или qa, так же можно поставить ! чтобы задать обратное условие

