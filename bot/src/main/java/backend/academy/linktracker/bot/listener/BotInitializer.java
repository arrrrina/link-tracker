package backend.academy.linktracker.bot.listener;

import backend.academy.linktracker.bot.client.TelegramClient;
import backend.academy.linktracker.bot.handler.CommandHandler;
import backend.academy.linktracker.bot.handler.UnknownCommand;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotInitializer {

    private final TelegramBot telegramBot;
    private final TelegramUpdateListener listener;
    private final TelegramClient telegramClient;

    private final List<CommandHandler> commandHandler;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(listener);

        List<BotCommand> commands = commandHandler.stream()
                .filter(h -> !(h instanceof UnknownCommand))
                .map(c -> new BotCommand(c.command(), c.description()))
                .toList();

        telegramClient.setCommands(commands);
    }
}
