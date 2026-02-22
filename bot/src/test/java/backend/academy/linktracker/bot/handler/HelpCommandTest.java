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
class HelpCommandTest {

    @Mock
    TelegramClient telegramClient;

    @InjectMocks
    HelpCommand helpCommand;

    @Test
    void shouldSendHelpMessage() {
        long chatId = 20L;
        Update update = TelegramTestUtils.update(1L, chatId, "/help");

        helpCommand.handle(update);

        verify(telegramClient).sendMessage(chatId, "/start - начать работу\n/help - список команд");
    }
}
