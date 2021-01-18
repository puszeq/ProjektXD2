package com.jp.pai.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "TODOLIST")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ManyToOne
    @Column(name = "userid")
    private User user;

    @NotNull
    private String task;

    @NotNull
    private boolean isDone;
}