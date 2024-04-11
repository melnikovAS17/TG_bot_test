package ru.twoWritersOfSomething.services.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.twoWritersOfSomething.dao.RawDataDAO;
import ru.twoWritersOfSomething.entities.RawData;
import ru.twoWritersOfSomething.services.MainService;
import ru.twoWritersOfSomething.services.ProducerService;

@Service
public class MainServiceImpl implements MainService {

    private final RawDataDAO rawDataDAO;
    private final ProducerService producerService;

    public MainServiceImpl(RawDataDAO rawDataDAO, ProducerService producerService) {
        this.rawDataDAO = rawDataDAO;
        this.producerService = producerService;
    }

    @Override
    public void processTextMessage(Update update) {
        saveRawData(update);
       /* SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello from some Node");
        producerService.produceAnswer(sendMessage);*/
    }

    private void saveRawData(Update update) {
        RawData rawData = new RawData();
        rawData.setEvent(update);
        rawDataDAO.save(rawData);
    }
}
