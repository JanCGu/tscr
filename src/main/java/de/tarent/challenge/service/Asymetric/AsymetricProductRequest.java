package de.tarent.challenge.service.Asymetric;

/**
 *
 * @author Jan
 */
public class AsymetricProductRequest<List, ProductRequest> extends AsymetricResponse<ProductRequest, List>{

    public AsymetricProductRequest(RequestBoundary<List> requestSender) {
        super(requestSender);
    }

    @Override
    public ProductRequest apply(List input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
