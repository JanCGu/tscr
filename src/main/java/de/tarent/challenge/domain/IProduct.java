/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.domain;

import java.util.Set;

/**
 * Represents an Interface to Product.
 * A Product represents an enty of something which can be bought in a shop.
 * @author Jan
 */
public interface IProduct {

    Set<String> getEans();

    String getName();

    String getSku();
    
}
