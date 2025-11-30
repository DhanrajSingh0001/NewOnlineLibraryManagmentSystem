package model;

public class User {
    private int userId;
    private String name;
    private String role; // admin or user

    public User(int userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role.toLowerCase();
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}

