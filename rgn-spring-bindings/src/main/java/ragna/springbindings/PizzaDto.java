package ragna.springbindings;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Arrays;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class PizzaDto {

    @JsonProperty("clientName")
    private String clientName;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("toppings")
    private Toppings toppings;

    public PizzaDto() {
    }

    public PizzaDto(String clientName, BigDecimal price, Toppings toppings) {
        this.clientName = clientName;
        this.price = price;
        this.toppings = toppings;
    }

    public enum Toppings {
        PEPPERONI("PEPPERONI"), SUPREME("SUPREME");

        private final String value;

        Toppings(String value) { this.value = value; }

        @Override
        public String toString() { return value; }

        public static Toppings fromValue(String value) {
            return Arrays.stream(values())
                    .filter(t -> t.value.equalsIgnoreCase(value))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Topping Value: " + value));
        }
    }

    public static void main(String[] args) {
        System.out.println(Toppings.fromValue("PEPPERONIe"));
    }

}
