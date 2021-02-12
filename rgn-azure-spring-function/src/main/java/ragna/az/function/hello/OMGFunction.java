package ragna.az.function.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ragna.az.function.hello.model.ItHappens;
import ragna.az.function.hello.model.OMG;

import java.util.function.Function;

@Component("omg")
@Slf4j
public class OMGFunction implements Function<ItHappens, OMG> {
    @Override
    public OMG apply(ItHappens itHappens) {
        log.info("OMG function called! {}", itHappens);
        return new OMG(String.format("OMG! Don't believe %s happened!\n", itHappens.getMessage()));
    }
}
