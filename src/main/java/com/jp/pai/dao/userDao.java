package com.jp.pai.dao;

import com.jp.pai.entities.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface userDao extends CrudRepository<User, Integer> {
    public User findByLogin(String login);
}

