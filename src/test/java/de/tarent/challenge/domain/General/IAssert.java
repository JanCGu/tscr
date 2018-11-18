package de.tarent.challenge.domain.General;

/**
 * IAssert is an interface for assert classes containing if the creation with
 * the information from this class is expected to fail.
 *
 * @author Jan
 */
public interface IAssert {

    /**
     * Is true if the test using the implementation of this interface should
     * fail.
     *
     * @return
     */
    public boolean expectedToFail();
}
