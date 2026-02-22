package backend.academy.linktracker.bot.client;

import com.pengrad.telegrambot.model.BotCommand;
import java.util.List;

public interface TelegramClient {

    void sendMessage(long chatId, String text);

    void setCommands(List<BotCommand> commands);
}
