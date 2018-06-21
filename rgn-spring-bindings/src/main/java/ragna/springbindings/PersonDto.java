package ragna.springbindings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class PersonDto {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("secondName")
    private String secondName;
    // Formats output date when this DTO is passed through JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    // Allows dd/MM/yyyy date to be passed into GET request in JSON
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("dateOfBirth")
    private Date dateOfBirth;

    @JsonProperty("profession")
    private String profession;
    @JsonProperty("salary")
    private BigDecimal salary;


    public PersonDto() {
    }

    public PersonDto(String firstName, String secondName, Date dateOfBirth, String profession, BigDecimal salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.profession = profession;
        this.salary = salary;
    }
}
