package com.sx.models;

import javax.persistence.*;

/**
 * Created by udr013 on 3-6-2016.
 */
@Entity
public class User_roles {
    @Id
    @Column(name="user_role_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    int user_role_id;
    String username;
    String role;

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        System.out.println(username);
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User_roles{" +
                "user_role_id=" + user_role_id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
