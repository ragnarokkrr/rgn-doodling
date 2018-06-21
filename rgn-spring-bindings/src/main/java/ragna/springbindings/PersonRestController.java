package ragna.springbindings;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @GetMapping("/getWithRequestParam")
    public List<String> getWithRequestParam(
        @RequestParam("personDTO") String personDto) throws IOException{

        PersonDto mapperPerson  = null;
        mapperPerson = new ObjectMapper()
            .setDateFormat(SIMPLE_DATE_FORMAT)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .readValue(personDto, PersonDto.class);

        return Arrays.asList(
                mapperPerson.getFirstName(),
                mapperPerson.getSecondName()
        );
    }

    @GetMapping("/getWithRequestParamList")
    public List<String> getWithRequestParamList(
            @RequestParam("personDTOList") String personDto) throws IOException{

        PersonDto[] mapperPerson  = null;
        mapperPerson = new ObjectMapper()
                .setDateFormat(SIMPLE_DATE_FORMAT)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .readValue(personDto, PersonDto[].class);

        return Arrays.asList(
                mapperPerson[0].getFirstName(),
                mapperPerson[0].getSecondName()
        );
    }


    @GetMapping("/getWithRequestParamListBind")
    public List<String> getWithRequestParamList(
            @RequestParam("personDTOList") PersonDto[] personDto) throws IOException{

        PersonDto[] mapperPerson  = personDto;

        return Arrays.asList(
                mapperPerson[0].getFirstName(),
                mapperPerson[0].getSecondName()
        );
    }


    @GetMapping("/getWithRequestEnum")
    public List<String> getWithRequestEnum(
            @RequestParam("pizzaDTO") PizzaDto pizzaDto) throws IOException{

        PizzaDto dto = null;
        dto = pizzaDto;

        return Arrays.asList(
                dto.getClientName(),
                dto.getPrice().toString(),
                dto.getToppings().toString()
        );
    }


    @GetMapping("/getWithRequestArray")
    public List<String> getWithRequestArray(
            @RequestParam("name") String name,
            @RequestParam("salary")BigDecimal salary,
            @RequestParam("filterKey") String[] key,
            @RequestParam("filterValue") String[] value) throws IOException{


        return Arrays.asList(
                name,
                salary.toString(),
                Arrays.toString(key),
                Arrays.toString(value)
        );
    }

    @GetMapping("/getWithRequestArrayZip")
    public List<String> getWithRequestArrayZip(
            @RequestParam("name") String name,
            @RequestParam("salary")BigDecimal salary,
            @RequestParam("filterKey") String[] key,
            @RequestParam("filterValue") String[] value) throws IOException{

        if(Objects.isNull(key) || Objects.isNull(value) || key.length != value.length)
            throw new IllegalArgumentException("filterKey doesnt match filterValue");

        List<String> mappedKeyVal = IntStream.range(0, key.length)
                .mapToObj(i -> String.format("filterKey: %s, filterValue: %s", key[i], value[i]))
                .peek(System.out::println)
                .collect(Collectors.toList());

        return Arrays.asList(
                name,
                salary.toString(),
                Arrays.toString(key),
                Arrays.toString(value),
                mappedKeyVal.toString()
        );
    }


}
