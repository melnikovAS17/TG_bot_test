package ru.twoWritersOfSomething.services.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.twoWritersOfSomething.services.UpdateProducer;


@Service
public class UpdateProducerImpl implements UpdateProducer {

    private final static Logger logger = Logger.getLogger(UpdateProducerImpl.class);

    private final RabbitTemplate rabbitTemplate;

    public UpdateProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void produce(String rabbitQueue, Update update) {
        logger.debug(update.getMessage().getText() + " : dispatcher -> отправил в очередь");
        rabbitTemplate.convertAndSend(rabbitQueue, update);
    }
}
