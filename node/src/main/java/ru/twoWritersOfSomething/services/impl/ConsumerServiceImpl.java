package ru.twoWritersOfSomething.services.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.twoWritersOfSomething.services.ConsumerService;
import ru.twoWritersOfSomething.services.MainService;


import static ru.twoWriterOfSomething.model.RabbitQueue.*;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final static Logger logger = Logger.getLogger(ConsumerServiceImpl.class);

    private final MainService mainService;

    public ConsumerServiceImpl(MainService mainService) {
        this.mainService = mainService;
    }

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessageUpdate(Update update) {
        logger.debug(update.getMessage().getText());
        logger.debug("NODE: Text message is received");
        mainService.processTextMessage(update);
    }

    @Override
    @RabbitListener(queues = DOC_MESSAGE_UPDATE)
    public void consumeDocMessageUpdate(Update update) {
        logger.debug("NODE: Doc message is received");
    }

    @Override
    @RabbitListener(queues = PHOTO_MESSAGE_UPDATE)
    public void consumePhotoMessageUpdate(Update update) {
        logger.debug("NODE: Photo message is received");
    }
}
