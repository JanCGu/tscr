package de.tarent.challenge.service.Asymetric;

import com.google.common.base.Function;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * It handels the incoming request and then sends the Response outward.
 * An concrete implementation has to implement how the Request is translated to the Response.
 * 
 * The class is a subject for outgoing information an therefore pushes the information to the registered subscriber.
 * The class is a observer and therefore gets the information of what kind of object should be send out.
 * 
 * @author Jan
 * @param <Response> This type represents an object which will be send out.
 * @param <Request> This is the type of the object which will be send to trigger the response.
 */
@Service
public abstract class AsymetricResponse<Response,Request> extends RequestBoundary<Response> implements Consumer<Request>,Function<Request,Response> {

    public AsymetricResponse(RequestBoundary<Request> requestSender)
    {
        requestSender.Subscribe(this);
    }

    /**
     * Consumes the request t and triggers the push to the outgoing object.
     * @param t 
     */
    @Override
    public void accept(Request t) {
        Push(apply(t));
    }
}
