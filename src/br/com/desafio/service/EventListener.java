package br.com.desafio.service;

public interface EventListener { // Observer
    void update(final EventEnum eventType); // Subject
}