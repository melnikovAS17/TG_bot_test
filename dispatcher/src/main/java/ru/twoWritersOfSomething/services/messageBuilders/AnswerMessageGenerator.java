package ru.twoWritersOfSomething.services.messageBuilders;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.twoWritersOfSomething.services.callbackHandl.CallBackHandler;
import ru.twoWritersOfSomething.services.messageBuilders.keyboards.InlineResponseBotCapabilities;
import ru.twoWritersOfSomething.services.messageBuilders.keyboards.InlineResponseGenderChoice;
import ru.twoWritersOfSomething.services.messageBuilders.keyboards.ReplyKeyWelcomeCommands;

@Service
public class AnswerMessageGenerator {
    private final ReplyKeyWelcomeCommands replyKeyWelcomeCommands;
    private final InlineResponseBotCapabilities inlineResponseBotCapabilities;
    private final InlineResponseGenderChoice inlineResponseGenderChoice;

    private final CallBackHandler callBackHandler;


    public AnswerMessageGenerator(ReplyKeyWelcomeCommands replyKeyWelcomeCommands, InlineResponseBotCapabilities inlineResponseBotCapabilities, InlineResponseGenderChoice inlineResponseGenderChoice, CallBackHandler callBackHandler){
        this.replyKeyWelcomeCommands = replyKeyWelcomeCommands;
        this.inlineResponseBotCapabilities = inlineResponseBotCapabilities;
        this.inlineResponseGenderChoice = inlineResponseGenderChoice;
        this.callBackHandler = callBackHandler;
    }

    // TODO добавить реакцию на различные команды
    public SendMessage getAnswer(Update update) {
        SendMessage sendMessage = new SendMessage();
        long chat_id = update.getMessage().getChatId();


        if (update.getMessage().getText().equals("/start")) {
            sendMessage = replyKeyWelcomeCommands.getStartKeyboard(chat_id);

        } else if (update.getMessage().getText().equals("Информация")) {
            // обязательно добавлять chatID в message
            sendMessage = inlineResponseBotCapabilities.sendInlineKeyboardMessage(chat_id);
        } else if (update.getMessage().getText().equals("Время работы")) {
                // обязательно добавлять chatID в message
            sendMessage.setChatId(chat_id);
            sendMessage.setText("Зал работает ежедневно (без выходных) с 8-00 по 22-00.");
        }else if (update.getMessage().getText().equals("Тренера")) {
                // обязательно добавлять chatID в message
                sendMessage = inlineResponseGenderChoice.sendInlineKeyboardMessage(chat_id);

        } else if (update.getMessage().getText().equals("Тест возможностей бота")) {
            sendMessage = inlineResponseBotCapabilities.sendInlineKeyboardMessage(chat_id);

        } else {
            sendMessage.setChatId(chat_id);
            sendMessage.setText("Неподдерживаемая команда, используйте /start");
        }

        return sendMessage;
    }
}
