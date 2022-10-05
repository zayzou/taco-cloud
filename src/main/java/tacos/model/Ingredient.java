package tacos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String id;
    private String name;
    private Type type;

    public enum Type {
      WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    };
}
