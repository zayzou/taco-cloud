package tacos.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5,message = "Issem n utacos ilaq ad yesɛu 05 naɣ  kthar")
    private String name;

    @NotNull
    @Size(min = 1,message = "ilaq attekhthared au moins yiwen utacos")
    @ManyToMany
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    };

}
