package backend.academy.linktracker.bot.handler;

import static org.mockito.Mockito.verify;

import backend.academy.linktracker.bot.client.TelegramClient;
import backend.academy.linktracker.bot.repository.UserRepository;
import backend.academy.linktracker.bot.support.TelegramTestUtils;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StartCommandTest {

    @Mock
    TelegramClient telegramClient;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    StartCommand startCommand;

    @Test
    void shouldRegisterNewUserAndSendWelcomeMessage() {
        long userId = 1L;
        long chatId = 10L;

        Update update = TelegramTestUtils.update(userId, chatId, "/start");

        startCommand.handle(update);

        verify(telegramClient)
                .sendMessage(10L, "Добро пожаловать! Используйте /help, чтобы посмотреть другие команды.");
    }
}
