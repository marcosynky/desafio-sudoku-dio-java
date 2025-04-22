package br.com.desafio.service;

import br.com.desafio.ui.custom.input.NumberText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.desafio.service.EventEnum.CLEAR_SPACE; // Importa a notificação CLEAR_SPACE


public class NotifierServices { // Serviço de notificação
    private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>() {{ // Mapa de notificações
        put(CLEAR_SPACE, new ArrayList<>()); // Adiciona a notificação CLEAR_SPACE
    }};

    public void notify(final EventEnum eventType){ // Notifica as notificações
        listeners.get(eventType).forEach(l -> l.update(eventType)); // Itera por todas as notificações
    }

    public void subscribe(EventEnum eventType, NumberText listener) { // Subscreve as notificações
        listeners.get(eventType).add(listener); // Adiciona a notificação
    }
}