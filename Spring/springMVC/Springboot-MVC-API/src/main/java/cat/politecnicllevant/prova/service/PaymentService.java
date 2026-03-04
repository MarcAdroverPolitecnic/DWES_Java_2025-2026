package cat.politecnicllevant.prova.service;

import com.paypal.base.rest.APIContext;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final APIContext apiContext;

    public PaymentService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    public void pay(){
        System.out.println(apiContext);
    }
}
