package ru.twoWritersOfSomething.services;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateProducer {
    void produce(String rabbitMq, Update update);
}
