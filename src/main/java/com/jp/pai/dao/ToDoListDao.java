package com.jp.pai.dao;

import com.jp.pai.entities.ToDoList;
import com.jp.pai.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToDoListDao extends CrudRepository<ToDoList, Integer> {
    public List<ToDoList> findByUser_Userid(Integer userId);
}

