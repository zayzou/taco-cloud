package tacos.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5,message = "Issem n utacos ilaq ad yesɛu 05 naɣ  kthar")
    private String name;

    @NotNull
    @Size(min = 1,message = "ilaq attekhthared au moins yiwen utacos")
    private List<Ingredient> ingredients;
}
