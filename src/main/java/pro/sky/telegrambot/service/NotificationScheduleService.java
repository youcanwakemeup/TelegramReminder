package pro.sky.telegrambot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NotificationScheduleService {
    private final NotificationTaskRepository notificationTaskRepository;
    private final TelegramBotUpdatesListener telegramBotUpdatesListener;
    public NotificationScheduleService(NotificationTaskRepository notificationTaskRepository, TelegramBotUpdatesListener telegramBotUpdatesListener) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.telegramBotUpdatesListener = telegramBotUpdatesListener;
    }

    @Scheduled(cron = "0 * * * * *")
    public void lookForNotifications() {
        LocalDateTime scheduledTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<NotificationTask> notificationTaskList = notificationTaskRepository.findByScheduledTime(scheduledTime);
        for (NotificationTask notificationTask : notificationTaskList) {
            telegramBotUpdatesListener.sendMessage(notificationTask);
        }
    }
}
