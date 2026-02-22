package backend.academy.linktracker.bot.handler;

import com.pengrad.telegrambot.model.Update;

public interface CommandHandler {
    String command();

    String description();

    void handle(Update update);
}
