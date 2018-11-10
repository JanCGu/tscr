package de.tarent.challenge.service.Asymetric;

import java.util.function.Consumer;

/**
 * The IRequstBoundy can send a message to the reciver. This Interface is a
 * generic form because it is at least needed for a request message might be a
 * string and for an answer where the message might be a product.
 *
 * It is sufficiant to have Consuemr<Message> implementation, because Push is
 * void.
 *
 * @author Jan
 */
public interface IRequestBoundary<Message> {

    /**
     * If a subscriber subscribes to the IRB (IRequestBoundary) he will be
     * notified if the instance does a Push.
     * @param subscriber
     */
    void Subscribe(Consumer<Message> subscriber);

    /**
     * Unregisters the subscriber. It will no longer be notified if a Push happens.
     * @param subscriber 
     */
    void Unsubscribe(Consumer<Message> subscriber);

    /**
     * Pushes the Message to all subscribers.
     * @param message 
     */
    void Push(Message message);

}
