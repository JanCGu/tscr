/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.service.Asymetric;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A concrete implementation which will send a Message object to its subscribers.
 * @author Jan
 * @param <Message> An instance of this type will be send to the subscribers.
 */
public class RequestBoundary<Message> implements IRequestBoundary<Message> {

    private final Set<Consumer<Message>> subscribers;
    
    public RequestBoundary()
    {
        subscribers = new HashSet<>();
    }
    
    /**
     * Adds the subscriber to the list which will be notified by a Push
     * @param subscriber 
     */
    @Override
    public void Subscribe(Consumer<Message> subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * The subscriber will no longer be notified by a push.
     * @param subscriber 
     */
    @Override
    public void Unsubscribe(Consumer<Message> subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Sends the message to all subscribers
     * @param message 
     */
    @Override
    public void Push(Message message) {
        subscribers.forEach(subscriber ->subscriber.accept(message));
    }
    
}
