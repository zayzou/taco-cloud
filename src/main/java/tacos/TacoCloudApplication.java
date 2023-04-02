package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.data.IngredientRepository;
import tacos.data.UserRepository;
import tacos.model.Ingredient;
import tacos.model.User;
import tacos.simpleflow.FileWriterGateway;
import static tacos.model.Ingredient.Type;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady(ApplicationReadyEvent event) {
        System.out.println(">>>Application ready at: " + event.getTimestamp());
    }


    @Order(1)
    @Bean
    public CommandLineRunner loadData(IngredientRepository repo, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("command line runner instance one is done");
            User user = new User();
            user.setPassword(passwordEncoder.encode("pass"));
            user.setUsername("user");
            user.setRole("ROLE_ADMIN");
            userRepository.save(user);
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

    @Bean
    public CommandLineRunner writeData(FileWriterGateway gateway, Environment env) {
        return args -> {
            String[] activeProfiles = env.getActiveProfiles();
            if (activeProfiles.length > 0) {
                String profile = activeProfiles[0];
                gateway.writeToFile("simple.txt", "Hello, Spring Integration! (" + profile + ")");
            } else {
                System.out.println("No active profile set. Should set active profile to one of xmlconfig, javaconfig, or javadsl.");
            }
        };
    }


}
