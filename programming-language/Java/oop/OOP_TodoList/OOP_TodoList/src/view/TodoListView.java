package view;

import service.TodoListService;
import util.InputUtil;

public class TodoListView {

    private TodoListService todoListService;

    public TodoListView(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    public void showTodoList() {
        while (true) {
            todoListService.showTodolist();

            System.out.println("Menu : ");
            System.out.println("1. Add");
            System.out.println("2. Remove");
            System.out.println("x. Exit");

            var input = InputUtil.input("Enter Choice").toLowerCase();
            if (input.equals("1")) {
                addTodoList();
            } else if (input.equals("2")) {
                removeTodoList();
            } else if (input.equals("x")) {
                break;
            } else {
                System.out.println("Invalid Choice");
            }
        }
    }

    public void addTodoList() {
        System.out.println("Adding TODOLIST");

        var todo = InputUtil.input("Todo (x for cancel)");
        if (todo.equals("x") || todo.equals("X")) {

        } else {
            todoListService.addTodolist(todo);
        }
    }

    public void removeTodoList() {
        System.out.println("REMOVING TODOLIST");

        var number = InputUtil.input("What Todo Number Remove? (x for cancel)");

        if (number.equals("x") || number.equals("X")) {

        } else {
            todoListService.removeTodolist(Integer.valueOf(number));
        }
    }
}
