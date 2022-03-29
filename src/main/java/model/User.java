package model;

public class User {
    private long id;
    private String name;
    private String password;
    private long roleId;

    // constructor without ID
    public User(String name, String password, long roleId) {
        this.name = name;
        this.password = password;
        this.roleId = roleId;
    }

    // constructor with ID
    public User(long id, String name, String password, long roleId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
