package ragna.argumentresolver;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsightController {


    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Insight> processFooBar(@RequestParam int limit, Insight insight) {

        return ResponseEntity.ok(insight);
    }
}
