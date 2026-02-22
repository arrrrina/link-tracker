package backend.academy.linktracker.bot.listener;

import backend.academy.linktracker.bot.dispatcher.CommandDispatcher;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramUpdateListener implements UpdatesListener {

    private final CommandDispatcher dispatcher;

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            if (update.message() != null && update.message().text() != null) {

                log.atInfo()
                        .addKeyValue("event", "update_received")
                        .addKeyValue("user_id", update.message().from().id())
                        .addKeyValue("text", update.message().text())
                        .log();

                dispatcher.dispatch(update);
            }
        }
        return CONFIRMED_UPDATES_ALL;
    }
}
