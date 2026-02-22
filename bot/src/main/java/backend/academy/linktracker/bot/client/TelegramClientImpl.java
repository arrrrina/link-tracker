package backend.academy.linktracker.bot.client;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramClientImpl implements TelegramClient {

    private final TelegramBot telegramBot;

    @Override
    public void sendMessage(long chatId, String text) {
        SendMessage request = new SendMessage(chatId, text);
        telegramBot.execute(request);
    }

    @Override
    public void setCommands(List<BotCommand> commands) {
        telegramBot.execute(new SetMyCommands(commands.toArray(new BotCommand[0])));
    }
}
