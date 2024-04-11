package ru.twoWritersOfSomething.services.messageBuilders.keyboards;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.twoWritersOfSomething.configs.keyboards.ReplyKeyboardMarkupBuilder;

@Service
public class ReplyKeyWelcomeCommands {

    public SendMessage getStartKeyboard (long chat_id) {
        return ReplyKeyboardMarkupBuilder.create(chat_id)
                .setText("Вас приветствует бот зала: 'ОООСПОРКЛУБ!' Список возможных действий указан в клавиатуре ниже.")
                .row()
                .button("Тренера")
                .button("Время работы")
                .button("Информация")
                .endRow()
                .row()
                .button("Тест возможностей бота")
                .endRow()
                .build();
    }
}
