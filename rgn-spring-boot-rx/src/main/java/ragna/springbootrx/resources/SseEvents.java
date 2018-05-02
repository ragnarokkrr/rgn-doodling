package ragna.springbootrx.resources;

import io.jmnarloch.spring.boot.rxjava.async.ObservableSseEmitter;

import io.reactivex.Observable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SseEvents {

    @RequestMapping(method = RequestMethod.GET, value="/messages")
    public ObservableSseEmitter<String> messages() {
        return new ObservableSseEmitter<String>(
                Observable.just("message 1", "message 2", "message 3")
        );
    }
}
