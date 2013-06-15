package business;

import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class AsyncService {

    @Asynchronous
    public Future<String> sayHelloAsync() {
        return new AsyncResult<String>("Hello World");
    }
}
