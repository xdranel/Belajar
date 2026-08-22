package service;

import entity.Todolist;

public interface TodoListService {

    void showTodolist();

    void addTodolist(String todo);

    void removeTodolist(Integer number);
}
