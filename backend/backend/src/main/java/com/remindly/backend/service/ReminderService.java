package com.remindly.backend.service;

import com.remindly.backend.dto.CreateReminderRequest;
import com.remindly.backend.dto.ReminderResponse;
import com.remindly.backend.entity.Reminder;
import com.remindly.backend.entity.User;
import com.remindly.backend.exception.UserNotFoundException;
import com.remindly.backend.repository.ReminderRepository;
import com.remindly.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
    public class ReminderService {

        private final ReminderRepository reminderRepository;
private final UserRepository userRepository;
        public ReminderService(ReminderRepository reminderRepository, UserRepository userRepository) {
            this.reminderRepository = reminderRepository;
            this.userRepository = userRepository;
        }
    public ReminderResponse createReminder(
            CreateReminderRequest request,
            String email
    ) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException(
                    "User not found"
            );
        }
        Reminder reminder =
                new Reminder();
        reminder.setTitle(
                request.getTitle()
        );

        reminder.setDescription(
                request.getDescription()
        );

        reminder.setLink(
                request.getLink()
        );

        reminder.setDeadline(
                request.getDeadline()
        );

        reminder.setFavorite(
                request.isFavorite()
        );

        reminder.setCompleted(
                false
        );

        reminder.setCreatedAt(
                LocalDateTime.now()
        );

        reminder.setUpdatedAt(
                LocalDateTime.now()
        );

        reminder.setUser(
                user.get()
        );
        Reminder savedReminder =
                reminderRepository.save(reminder);

        return new ReminderResponse(

                savedReminder.getId(),

                savedReminder.getTitle(),

                savedReminder.getDescription(),

                savedReminder.getLink(),

                savedReminder.getDeadline(),

                savedReminder.isFavorite(),

                savedReminder.isCompleted(),

                savedReminder.getCreatedAt()

        );
    }
    }

