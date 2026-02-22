package backend.academy.linktracker.bot.handler;

import backend.academy.linktracker.bot.client.TelegramClient;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnknownCommand implements CommandHandler {

    private final TelegramClient telegramClient;

    @Override
    public String command() {
        return "unknown";
    }

    @Override
    public String description() {
        return "Неизвестная команда";
    }

    @Override
    public void handle(Update update) {
        Long chatId = update.message().chat().id();

        telegramClient.sendMessage(
                chatId, "Неизвестная команда. Используйте /help, чтобы посмотреть список доступных команд.");
    }
}
