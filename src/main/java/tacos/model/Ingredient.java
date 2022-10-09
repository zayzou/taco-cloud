package tacos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table
public class Ingredient implements Persistable<String> {

    @Id
    private String id;
    private String name;
    private Type type;

    @Override
    public boolean isNew() {
        return true;
    }

    public enum Type {
      WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    };
}
