package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.data.IngredientRepository;
import tacos.model.Ingredient;
import tacos.model.Taco;
import tacos.model.TacoOrder;
import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static tacos.model.Ingredient.Type;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@Slf4j
public class DesignController {

    private final IngredientRepository repo;

    public DesignController(IngredientRepository reps) {
        this.repo = reps;
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute("taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        Iterable<Ingredient> ingredients = repo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @GetMapping("")
    public String showDesignForm() {
        log.info("---------calling show design form-----------");
        return "design";
    }

    @PostMapping
    public String process(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info(taco.toString());
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
