package ragna.springbootrx.resources;

import io.reactivex.Observable;
import java.util.Date;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ragna.springbootrx.model.Invoice;

@RestController
public class InvoiceResource {

    @RequestMapping(method = RequestMethod.GET, value = "/invoices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Observable<Invoice> getInvoices() {
        return Observable.just(
            new Invoice("Acme", new Date()),
            new Invoice("Oceanic", new Date())
        );
    }

}
