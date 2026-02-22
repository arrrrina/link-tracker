package backend.academy.linktracker.bot.support;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;

public final class TelegramTestUtils {

    private TelegramTestUtils() {}

    public static Update update(Long userId, Long chatId, String text) {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);
        User user = mock(User.class);

        lenient().when(update.message()).thenReturn(message);

        lenient().when(message.chat()).thenReturn(chat);
        lenient().when(chat.id()).thenReturn(chatId);

        lenient().when(message.from()).thenReturn(user);
        lenient().when(user.id()).thenReturn(userId);

        lenient().when(message.text()).thenReturn(text);

        return update;
    }
}
