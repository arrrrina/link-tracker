package backend.academy.linktracker.bot.handler;

import static org.mockito.Mockito.verify;

import backend.academy.linktracker.bot.client.TelegramClient;
import backend.academy.linktracker.bot.support.TelegramTestUtils;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UnknownCommandTest {

    @Mock
    TelegramClient telegramClient;

    @InjectMocks
    UnknownCommand unknownCommand;

    @Test
    void shouldSendUnknownCommandMessage() {
        long chatId = 30L;
        Update update = TelegramTestUtils.update(5L, 30L, "/error");

        unknownCommand.handle(update);

        verify(telegramClient)
                .sendMessage(
                        chatId, "Неизвестная команда. Используйте /help, чтобы посмотреть список доступных команд.");
    }
}
