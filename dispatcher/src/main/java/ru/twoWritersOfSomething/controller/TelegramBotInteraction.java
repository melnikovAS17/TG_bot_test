package ru.twoWritersOfSomething.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class TelegramBotInteraction extends TelegramLongPollingBot {

    private static final Logger logger = Logger.getLogger(TelegramBotInteraction.class);

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String token;

    private final UpdateController updateController;

    public TelegramBotInteraction(UpdateController updateController) {
        this.updateController = updateController;
    }

    @PostConstruct
    public void init() {
        updateController.registerBotTG(this);
    }
    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
       updateController.validateIncomingData(update);
    }

    public void sendResponseMessageForUser(SendMessage message) {
        if (message != null){
            try {
                execute(message);
            } catch (TelegramApiException e) {
                logger.error(e);
            }
        }
    }
}
