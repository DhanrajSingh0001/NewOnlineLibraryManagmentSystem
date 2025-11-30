package dao;

import model.User;
import java.util.*;

public class UserDAO {
    private Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    public synchronized User addUser(String name, String role) {
        User user = new User(nextId++, name, role);
        users.put(user.getUserId(), user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User findUserById(int id) {
        return users.get(id);
    }
}

