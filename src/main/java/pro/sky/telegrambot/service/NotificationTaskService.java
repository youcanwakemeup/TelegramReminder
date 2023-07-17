package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;

@Service
public class NotificationTaskService {
    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    public void saveNotificationTask(Long chatId, String message, LocalDateTime scheduledTime) {
        NotificationTask notificationTask = new NotificationTask(chatId, message, scheduledTime);
        notificationTaskRepository.save(notificationTask);
    }
}
