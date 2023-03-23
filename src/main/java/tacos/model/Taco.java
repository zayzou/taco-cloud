package tacos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Taco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Issem n utacos ilaq ad yesɛu 05 naɣ  kthar")
    private String name;

    @NotNull
    @Size(min = 1, message = "ilaq attekhthared au moins yiwen utacos")
    @ManyToMany
    private List<Ingredient> ingredients;

    private Date createdAt = new Date();


    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }


}
