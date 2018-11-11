package de.tarent.challenge.util;

import com.google.common.base.Function;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Contains String checks.
 * @author Jan
 */
public class Check {
    
    /**
     * Checks if toCheck is non empty and returns it. Otherwise an
     * IllegalArugmentException is throw.
     *
     * @param toCheck
     * @param errorMsg the message of the Exception.
     * @return returns toCheck.
     * @throws IllegalArgumentException if the check fails this error will be
     * thrown.
     */
    public static String nonEmpty(String toCheck, String errorMsg) throws IllegalArgumentException {
        if (toCheck == null || toCheck.isEmpty()) {
            throw new IllegalArgumentException(errorMsg);
        }
        return toCheck;
    }
    
    /**
     * Checks that that at least one element exists and that no empty element exists.
     * @param <T> The type of the collection which should be checked to have at least an element which also is non empty.
     * @param toCheck the collection to check
     * @param isempty the funciton which evaluates if an element of type t is empty.
     * @param name the name which shall be menationed in the exception.
     * @return toCheck will be returned.
     * @throws IllegalArgumentException if the collection is empty or has a empty ellement this exception will be trhown.
     */
    public static <T>  Set<T> atLeastOne(Set<T> toCheck,Function<T,Boolean> isempty, String name) throws IllegalArgumentException {
        if (toCheck == null || toCheck.isEmpty()) {
            throw new IllegalArgumentException(name+" has no entries.");
        }
        long nullentries = toCheck.stream().filter(ean -> isempty.apply(ean)).count();
        if (nullentries > 0) {
            throw new IllegalArgumentException(name+" has empty entries.");
        }
        return toCheck;
    }
    
    /**
     * Checks that that at least one element exists and that no empty element exists.
     * @param <T> The type of the collection which should be checked to have at least an element which also is non empty.
     * @param toCheck the collection to check
     * @param isempty the funciton which evaluates if an element of type t is empty.
     * @param name the name which shall be menationed in the exception.
     * @return toCheck will be returned.
     * @throws IllegalArgumentException if the collection is empty or has a empty ellement this exception will be trhown.
     */
    public static <T>  List<T> atLeastOne(List<T> toCheck,Function<T,Boolean> isempty, String name) throws IllegalArgumentException {
        if (toCheck == null || toCheck.isEmpty()) {
            throw new IllegalArgumentException(name+" has no entries.");
        }
        long nullentries = toCheck.stream().filter(ean -> isempty.apply(ean)).count();
        if (nullentries > 0) {
            throw new IllegalArgumentException(name+" has empty entries.");
        }
        return toCheck;
    }
}
