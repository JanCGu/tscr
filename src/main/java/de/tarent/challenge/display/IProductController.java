/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.display;

import javax.naming.ServiceUnavailableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Jan
 */
public interface IProductController {

    /**
     * Deletes the products from the storage. If the product does not exist,it does nothing.
     * @param toUpdate
     * @return True if the operation was sucessfull.
     * @throws ServiceUnavailableException
     */
    @DeleteMapping
    boolean deleteProducts(@RequestBody Iterable<ProductModel> toUpdate) throws ServiceUnavailableException;

    /**
     * Returns a ProductModel of a Product by its sku. This product comes from the
     * persitance layer.
     * @param sku
     * @return If no Product was found null is returned.
     * @throws ServiceUnavailableException
     */
    @GetMapping(value = "/{sku}")
    ProductModel retrieveProductBySku(@PathVariable String sku) throws ServiceUnavailableException;

    /**
     * Returns an iterable "list" of ProducModels, which represents the products.
     * Gets all products available.
     * @return If no products exists null is returned.
     * @throws ServiceUnavailableException
     */
    @GetMapping
    Iterable<ProductModel> retrieveProducts() throws ServiceUnavailableException;

    /**
     * the products in the toUpdate are stored in the perstiance layer.
     * This means that they are eather created, if they do not exits or are updated
     * if they do exist.
     *
     * @param toUpdate
     * @return Returns true if the update was sucessful.
     * @throws ServiceUnavailableException
     */
    @PostMapping
    boolean updateProducts(@RequestBody Iterable<ProductModel> toDelete) throws ServiceUnavailableException;
    
}
