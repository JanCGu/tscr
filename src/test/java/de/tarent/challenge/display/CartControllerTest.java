package de.tarent.challenge.display;

import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.service.StumpCartGetter;
import de.tarent.challenge.service.StumpCartSetter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import javax.naming.ServiceUnavailableException;
import org.javamoney.moneta.Money;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test of the CartController Methods
 *
 * @author jan
 */
public class CartControllerTest {

    @Test
    public void testRestriveAllCarts() throws ServiceUnavailableException {
        Set<CartModel> setModel = getCarts();
        List<ICart> out = new ArrayList<>();
        out.addAll(setModel);
        StumpCartGetter stumpGet = new StumpCartGetter(out);
        StumpCartSetter stumpSet = new StumpCartSetter(true);
        
        CartController cc = new CartController(stumpGet, stumpSet);
        assertEquals("Expected to retrive out!",out, cc.retriveAllCarts());
        
        stumpGet.Output=null;
        assertNull("Expected to retrive out!",cc.retriveAllCarts());
        
    }

    @Test
    public void testRetiveCartById() throws ServiceUnavailableException {
        Set<CartModel> setModel = getCarts();
        List<ICart> out = new ArrayList<>();
        out.addAll(setModel);
        StumpCartGetter stumpGet = new StumpCartGetter(out);
        StumpCartSetter stumpSet = new StumpCartSetter(true);
        
        CartController cc = new CartController(stumpGet, stumpSet);
        assertEquals("Expected to retrive frist cart!",out.get(0), cc.retriveCartById("c1"));
        //c2 or a 'not found' cannot be retrived as the sump has no logic for it and still returns c1.
    }

    @Test
    public void testDeleteCart() throws ServiceUnavailableException {
        doSetOperation((cc, in) -> {
            try {
                return cc.deleteCart(in);
            } catch (ServiceUnavailableException ex) {
                assertFalse(true);
                return false;
            }
        });
    }

    @Test
    public void testUpdateCart() {
        doSetOperation((cc, in) -> {
            try {
                return cc.updateCart(in);
            } catch (ServiceUnavailableException ex) {
                assertFalse(true);
                return false;
            }
        });
    }

    private void doSetOperation(BiFunction<CartController, Set<CartModel>, Boolean> doIO) {
        
        Set<CartModel> setModel = getCarts();
        List<ICart> out = new ArrayList<>();
        out.addAll(setModel);
        StumpCartGetter stumpGet = new StumpCartGetter(out);
        StumpCartSetter stumpSet = new StumpCartSetter(true);

        CartController cc = new CartController(stumpGet, stumpSet);
        assertTrue(doIO.apply(cc, null));
        assertTrue(doIO.apply(cc, setModel));
        assertEquals("All Input was passed to the deletion", setModel, stumpSet.Input);
    }

    private Set<CartModel> getCarts() {
        Set<CartModel> ret = new HashSet<>();
        List<IProduct> products = new ArrayList<>();
        Set<String> eans = new HashSet<>();
        eans.add("ean1");
        products.add(new ProductModel("sku", "name", eans,Money.of(1,"EUR")));

        ret.add(new CartModel("c1", products));
        ret.add(new CartModel("c2", products));
        return ret;
    }
}
