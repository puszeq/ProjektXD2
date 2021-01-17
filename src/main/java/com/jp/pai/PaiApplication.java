package com.jp.pai;

import com.jp.pai.controllers.UserController;
import com.jp.pai.dao.userDao;
import com.jp.pai.entities.User;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PaiApplication {
    @Autowired
    private userDao dao;
    @Autowired
    private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PaiApplication.class, args);
	}
        
    @PostConstruct
    public void init() {
        User user = new User();
        user.setLogin("admin");
        user.setName("Admin");
        user.setSurname("Admin");
        user.setPassword(passwordEncoder.encode("passwd"));
        
        dao.save(user);
    }
}
