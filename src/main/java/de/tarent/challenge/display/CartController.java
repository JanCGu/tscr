package de.tarent.challenge.display;

import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.service.ICartGetter;
import de.tarent.challenge.service.ICartSetter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.naming.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Allows the Rest access to the carts.
 *
 * @author Jan
 */
@RestController
@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private ICartGetter cartGetter;
    @Autowired
    private ICartSetter cartSetter;

    /**
     * Allows to directly set the autowired variables.
     *
     * This declartion is atleast needed for testing.
     *
     * @param getter
     * @param setter
     */
    public CartController(ICartGetter getter, ICartSetter setter) {
        cartGetter = getter;
        cartSetter = setter;
    }
    
    protected CartController(){
        
    }

    @GetMapping
    public List<CartModel> retriveAllCarts() throws ServiceUnavailableException {
        if(cartGetter==null)
            throw new ServiceUnavailableException("Missing persitance layer!");
        return convert(cartGetter.All());
    }

    @GetMapping("/id")
    public CartModel retriveCartById(@PathVariable String id) throws ServiceUnavailableException {
        if(cartGetter==null)
            throw new ServiceUnavailableException("Missing persitance layer!");
        
        ICart ret = cartGetter.ById(id);
        if(ret == null)
            return null;
        return new CartModel(ret);
    }

    /**
     * Allows to delete a set of cartmodels from the storage.
     *
     * @param toDelete
     * @return if the operation was sucessfull.
     */
    @DeleteMapping
    public boolean deleteCart(@RequestBody Set<CartModel> toDelete) throws ServiceUnavailableException {
        if(cartSetter==null)
            throw new ServiceUnavailableException("Missing persitance layer!");
        return cartSetter.Delete(convert(toDelete));
    }

    /**
     * Allows to Update a Set of CartModels.
     *
     * @param toUpdate
     * @return
     */
    @PostMapping
    public boolean updateCart(@RequestBody Set<CartModel> toUpdate) throws ServiceUnavailableException {
        if(cartSetter==null)
            throw new ServiceUnavailableException("Missing persitance layer!");
        return cartSetter.Update(convert(toUpdate));
    }

    private Set<ICart> convert(Set<CartModel> toConvert) {
        if(toConvert==null)
            return null;
        Set<ICart> ret = new HashSet<>();
        toConvert.forEach(ret::add);
        return ret;
    }

    private List<CartModel> convert(List<ICart> toConvert) {
        if(toConvert==null)
            return null;
        List<CartModel> ret = new ArrayList<>();
        toConvert.forEach(cart -> ret.add(new CartModel(cart)));
        return ret;
    }
}
