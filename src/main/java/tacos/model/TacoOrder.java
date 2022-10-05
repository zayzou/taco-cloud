package tacos.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {

    @NotBlank
    private String deliveryName;
    @NotBlank
    private String deliveryStreet;
    @NotBlank
    private String deliveryCity;
    @NotBlank
    private String deliveryState;
    @NotBlank
    private String deliveryZip;
    @NotBlank
    @CreditCardNumber
    private String ccNumber;
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;
    @NotBlank
    @Digits(fraction = 0, integer = 3)
    private String ccCVV;


    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

}
