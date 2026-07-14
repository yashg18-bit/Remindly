package com.remindly.backend.repository;

import com.remindly.backend.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository
        extends JpaRepository<Reminder, Long> {

}