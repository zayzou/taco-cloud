package tacos.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Table
public class Taco {

    @Id
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5,message = "Issem n utacos ilaq ad yesɛu 05 naɣ  kthar")
    private String name;

    @NotNull
    @Size(min = 1,message = "ilaq attekhthared au moins yiwen utacos")
    private List<IngredientRef> ingredients;

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(new IngredientRef(ingredient.getId()));
    };

}
