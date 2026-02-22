package backend.academy.linktracker.bot.dispatcher;

import backend.academy.linktracker.bot.handler.CommandHandler;
import backend.academy.linktracker.bot.handler.UnknownCommand;
import com.pengrad.telegrambot.model.Update;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandDispatcher {

    private final Map<String, CommandHandler> handlers;
    private final UnknownCommand unknownCommand;

    public CommandDispatcher(List<CommandHandler> handlers, UnknownCommand unknownCommand) {
        this.unknownCommand = unknownCommand;

        this.handlers = handlers.stream()
                .filter(h -> !(h instanceof UnknownCommand))
                .collect(Collectors.toMap(CommandHandler::command, h -> h));
    }

    public void dispatch(Update update) {
        String text = update.message().text();
        Long userId = update.message().from().id();

        CommandHandler handler = handlers.getOrDefault(text, unknownCommand);

        log.atInfo()
                .addKeyValue("event", "command_dispatched")
                .addKeyValue("user_id", userId)
                .addKeyValue("command", text)
                .addKeyValue("handler", handler.getClass().getSimpleName())
                .log();

        handler.handle(update);
    }
}
