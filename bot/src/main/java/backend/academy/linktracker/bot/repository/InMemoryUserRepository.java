package backend.academy.linktracker.bot.repository;

import backend.academy.linktracker.bot.model.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> storage = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        storage.put(user.telegramId(), user);
    }

    @Override
    public boolean existsByTelegramId(Long telegramId) {
        return storage.containsKey(telegramId);
    }
}
