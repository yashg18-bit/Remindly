package com.remindly.backend.controller;

import com.remindly.backend.dto.CreateReminderRequest;
import com.remindly.backend.dto.ReminderResponse;
import com.remindly.backend.service.ReminderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }


    @PostMapping
    public ReminderResponse createReminder(

            @Valid @RequestBody
            CreateReminderRequest request

    ){

        return reminderService.createReminder(

                request,
                "yash@gmail.com"

        );

    }

}