package com.mihey.tgbot.controller;

import com.mihey.tgbot.model.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequestMapping("/")
public class BotController {

    private final Bot bot;

    @Autowired
    public BotController(Bot bot) {
        this.bot = bot;
    }

    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }

    @GetMapping
    public String getMessage() {

        return "hello";
    }
}
