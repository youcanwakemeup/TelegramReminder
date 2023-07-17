package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.service.NotificationTaskService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final NotificationTaskService notificaitonTaskService;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, NotificationTaskService notificaitonTaskService) {
        this.telegramBot = telegramBot;
        this.notificaitonTaskService = notificaitonTaskService;
    }

    Pattern pattern = Pattern.compile("([0-9.\\s:]{16})\\s(\\W+)");

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Long chatId = update.message().chat().id();
            String incomeMessage = update.message().text();
            Matcher matcher = pattern.matcher(incomeMessage);
            if (incomeMessage.equals("/start")) {
                String messageText = "Добро пожаловать! Введите напоминание в виде: 'ДД.ММ.ГГГГ ЧЧ:ММ *напоминание*'";
                SendMessage sendMessage = new SendMessage(chatId, messageText);
                telegramBot.execute(sendMessage);
            }
            else if (matcher.matches()) {
                String dateTimeString = matcher.group(1);
                String reminderText = matcher.group(2);
                LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                notificaitonTaskService.saveNotificationTask(chatId, reminderText, localDateTime);
                SendMessage sendMessage = new SendMessage(chatId, "Напоминание сохранено!");
                telegramBot.execute(sendMessage);
            }
            else {
                SendMessage sendMessage = new SendMessage(chatId, "Неверный формат напоминания!");
                telegramBot.execute(sendMessage);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    public void sendMessage(NotificationTask notificationTask) {
        SendMessage sendMessage = new SendMessage(notificationTask.getChatId(), notificationTask.getMessage());
        telegramBot.execute(sendMessage);
    }
}
