package ru.twoWritersOfSomething.services.callbackHandl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class CallBackHandler {


    public SendMessage callBackMessageHandler(Update update){
        SendMessage sendMessage = new SendMessage();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();

        switch (update.getCallbackQuery().getData()) {
            case "/price" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Стоимотсь одной тренровки - 500р" +
                        " Если это ваше первое занятие - стоимость 0 рублей! " +
                        "Стоимость тренировки с тренером необхрдимо уточнить..");
                return  sendMessage;
            case "/time" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Время занятий");
                return  sendMessage;
            case "/directions" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Виды занятий");
                return  sendMessage;
            case "/card" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Клубная карта");
                return  sendMessage;
            case "/shop" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("В магазине нашего клуба вы можете приобрести: ,,");
                return  sendMessage;
            case "/sportFood" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Спортивное питание, доступное в нашем магазине: ");
                return  sendMessage;
            case "/call" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Номер горячей линии: 8 (888) 888 88-88");
                return  sendMessage;
            case "/review" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Вы можете оставить отзыв на нашем сайте: //https/sport_club");
                return  sendMessage;
            case "/male" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Список наших тренеров");
                return  sendMessage;
            case "/female" :
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Список наших тренеров");
                return  sendMessage;
        }
        sendMessage.setChatId(chat_id);
        sendMessage.setText("Nothing");
        return sendMessage;
    }


}
