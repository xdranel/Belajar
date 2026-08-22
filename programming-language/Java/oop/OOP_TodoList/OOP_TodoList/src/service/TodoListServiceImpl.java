package service;

import entity.Todolist;
import repository.TodoListRepository;

public class TodoListServiceImpl implements TodoListService {

    private TodoListRepository todoListRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public void showTodolist() {
        Todolist[] model = todoListRepository.getAll();

        System.out.println("TODOLIST");
        for (int i = 0; i < model.length; i++) {
            var todolist = model[i];
            var number = i + 1;

            if (todolist != null) {
                System.out.println(number + ". " + todolist.getTodo());
            }
        }
    }

    @Override
    public void addTodolist(String todo) {
        Todolist todolist = new Todolist(todo);
        todoListRepository.add(todolist);
        System.out.println("Successfully added todo : " + todo);
    }

    @Override
    public void removeTodolist(Integer number) {
        boolean success = todoListRepository.remove(number);
        if (success) {
            System.out.println("Success removing todo : " + number);
        } else {
            System.out.println("Fail removing todo : " + number);
        }
    }
}
