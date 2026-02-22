package backend.academy.linktracker.bot.handler;

import backend.academy.linktracker.bot.client.TelegramClient;
import backend.academy.linktracker.bot.model.User;
import backend.academy.linktracker.bot.repository.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartCommand implements CommandHandler {

    private final TelegramClient telegramClient;
    private final UserRepository userRepository;

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Начать работу";
    }

    @Override
    public void handle(Update update) {

        Long userId = update.message().from().id();
        Long chatId = update.message().chat().id();

        if (!userRepository.existsByTelegramId(userId)) {
            userRepository.save(new User(userId));
        }

        telegramClient.sendMessage(chatId, "Добро пожаловать! Используйте /help, чтобы посмотреть другие команды.");

        log.atInfo()
                .addKeyValue("event", "response_sent")
                .addKeyValue("chat_id", chatId)
                .addKeyValue("command", "/start")
                .log();
    }
}
