package com.jp.pai.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;
    
    @NotNull
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Imię 2-30 liter.")
    private String name;
    
    @NotNull
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Nazwisko 2-30 liter.")
    private String surname;
    
    @NotNull
    @Pattern(regexp = "[a-zA-Z_0-9]{4,30}", message="Login 4-30 znaków, alfanumeryczne oraz '_'.")
    private String login;
    
    @NotNull
    @Size(min = 4, message="Hasło minimum 4 znaki.")
    private String password;
    
    public User() {}
    
    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
