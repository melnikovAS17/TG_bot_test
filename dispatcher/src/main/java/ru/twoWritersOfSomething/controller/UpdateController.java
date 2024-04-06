package ru.twoWritersOfSomething.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.twoWritersOfSomething.controller.configs.TelegramBotInteraction;
import ru.twoWritersOfSomething.services.UpdateProducer;
import ru.twoWritersOfSomething.utils.MessageUtils;

import static ru.twoWriterOfSomething.model.RabbitQueue.*;


// Класс для первичной валидации данных
@Component
public class UpdateController {

    private static final Logger logger = Logger.getLogger(UpdateController.class);

    private final MessageUtils messageUtils;

    private final UpdateProducer updateProducer;

    @Autowired
    public UpdateController(MessageUtils messageUtils, UpdateProducer updateProducer) {
        this.messageUtils = messageUtils;
        this.updateProducer = updateProducer;
    }

    // Автор создал подобную связь (не через автовайринг бина), чтобы избежать цикличексой зависимости
    private TelegramBotInteraction telegramBotInteraction;

    public void registerBotTG(TelegramBotInteraction telegramBotInteraction) {
        this.telegramBotInteraction = telegramBotInteraction;
    }

    public void validateIncomingData(Update update) {
        if (update == null) {
            logger.error("Received update is null!");
            return;
        }

        if (update != null){
            distributeMessageByType(update);
        }else {
            logger.error("Received unsupported message type!");
        }
    }

    private void distributeMessageByType(Update update) {
        Message message = update.getMessage();
        if (message.getText() != null) {
            processTextMessage(update);
        } else if (message.getDocument() != null) {
            processDocMessage(update);
        } else if (message.getPhoto() != null) {
            processPhotoMessage(update);
        } else {
            setUnsupportedMessageTypeView(update);
        }
    }

    private void setUnsupportedMessageTypeView(Update update) {
        SendMessage sendMessage = messageUtils.generateSendMessageWithText(update,
                "Unsupported type of message!");
        setView(sendMessage);
    }

    private void setView(SendMessage sendMessage) {
        telegramBotInteraction.sendResponseMessageForUser(sendMessage);
    }

    private void responseForUserAboutReceivingMessage(Update update) {
        SendMessage message = messageUtils.generateSendMessageWithText(update,
                "File received, processing in progress");
        setView(message);
    }

    private void processPhotoMessage(Update update) {
        updateProducer.produce(PHOTO_MESSAGE_UPDATE ,update);
        responseForUserAboutReceivingMessage(update);
    }

    private void processDocMessage(Update update) {
        updateProducer.produce(DOC_MESSAGE_UPDATE ,update);
        responseForUserAboutReceivingMessage(update);
    }

    private void processTextMessage(Update update) {
        updateProducer.produce(TEXT_MESSAGE_UPDATE ,update);
    }
}
