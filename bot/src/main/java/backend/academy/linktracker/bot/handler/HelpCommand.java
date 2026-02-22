package backend.academy.linktracker.bot.handler;

import backend.academy.linktracker.bot.client.TelegramClient;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelpCommand implements CommandHandler {

    private final TelegramClient telegramClient;

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "Список команд";
    }

    @Override
    public void handle(Update update) {
        Long chatId = update.message().chat().id();

        telegramClient.sendMessage(chatId, "/start - начать работу\n/help - список команд");
    }
}
