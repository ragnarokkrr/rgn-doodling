package ragna.springwebtst.bindings;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BindingsController {
    @RequestMapping("/greeting2")
    public String greeting() {
        return "OK";
    }
}
