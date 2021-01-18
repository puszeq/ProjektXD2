package com.jp.pai.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

}
