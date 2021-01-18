package com.jp.pai.controllers;

import com.jp.pai.dao.ToDoListDao;
import com.jp.pai.dao.UserDao;
import com.jp.pai.entities.ToDoList;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoListDao toDoListDao;
    private final UserDao userDao;

    @GetMapping("/toDoList/{userId}")
    public List<ToDoList> getToDoList(@PathVariable("userId") Integer userId) {
        return toDoListDao.findByUser_Userid(userId);
    }

    @PostMapping("/toDoList/{userId}")
    public ToDoList addItem(@PathVariable("userId") Integer userId, @RequestBody String task) {
        ToDoList toDoList = ToDoList.builder()
                .task(task)
                .user(userDao.findByUserid(userId))
                .build();

        return toDoListDao.save(toDoList);
    }

    @DeleteMapping("/toDoList/{id}")
    public void deleteToDoList(@PathVariable("id") Integer id) {
        toDoListDao.deleteById(id);
    }

    @PutMapping("/toDoList")
    public ToDoList updateItem(@RequestBody ToDoList toDoList) {
        ToDoList existingTask = toDoListDao.findById(toDoList.getId())
                .orElseThrow(() -> new RuntimeException("Task not exist"));

        existingTask.setTask(toDoList.getTask());
        existingTask.setDone(toDoList.isDone());

        return toDoListDao.save(existingTask);
    }
}
