package ru.twoWritersOfSomething.services.messageBuilders.keyboards;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.twoWritersOfSomething.configs.keyboards.InlineKeyboardMarkupBuilder;

@Service
public class InlineResponseGenderChoice {

    public SendMessage sendInlineKeyboardMessage(long chat_id) {
        SendMessage keyboard;
        keyboard = InlineKeyboardMarkupBuilder.create(chat_id)
                .setText("Мужчина или женщина? ")
                .row()
                .button("Мужчина " +"\uD83D\uDC71", "/male")
                .button("Женщина " + "\uD83D\uDC69", "/female")
                .endRow()
                .build();
        return keyboard;
    }
}
