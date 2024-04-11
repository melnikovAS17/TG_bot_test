package ru.twoWritersOfSomething.services.messageBuilders.keyboards;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.twoWritersOfSomething.configs.keyboards.InlineKeyboardMarkupBuilder;

@Service
public class InlineResponseBotCapabilities {

    public SendMessage sendInlineKeyboardMessage(long chat_id) {
        SendMessage keyboard;
        keyboard = InlineKeyboardMarkupBuilder.create(chat_id)
                .setText("Выберите, что вас интересует: ")
                .row()
                .button("Стоимость " +  "\uD83D\uDCB5", "/price" )
                .button("График занятий " + "\uD83D\uDDD3", "/time")
                .button("Направления " + "\uD83D\uDCAA", "/directions" )
                .endRow()
                .row()
                .button("Клубная карта " + "\uD83E\uDEAA", "/card")
                .button("Магазин " + "\uD83D\uDECD\uFE0F", "/shop")
                .button("Спортпит " + "\uD83E\uDDF4", "/sportFood")
                .endRow()
                .row()
                .button("Связь с оператором" + "\uD83E\uDDCF\u200D♀\uFE0F", "/call")
                .endRow()
                .row()
                .button("Оставить отзыв", "/review")
                .endRow()
                .build();
        return keyboard;

    }
}
