package com.mihey.tgbot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "telegrambot")
public class Bot extends TelegramWebhookBot {

    private String botUsername;
    private String botToken;
    private String botPath;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        String message = getMessage();
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();

            try {
                execute(new SendMessage(chat_id, message));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getMessage() {
        StringBuilder output = new StringBuilder();

        try {
            Process process = Runtime.getRuntime().exec("ls -l /home");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
