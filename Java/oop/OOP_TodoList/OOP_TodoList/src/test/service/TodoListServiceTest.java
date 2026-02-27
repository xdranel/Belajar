package test.service;

import entity.Todolist;
import repository.TodoListRepository;
import repository.TodoListRepositoryImpl;
import service.TodoListService;
import service.TodoListServiceImpl;

public class TodoListServiceTest {
    public static void main(String[] args) {
        testRemoveTodolist();
    }

    public static void testShowTodolist() {
        TodoListRepositoryImpl todoListRepository = new TodoListRepositoryImpl();
        todoListRepository.data[0] = new Todolist("Belajar Java Dasar");
        todoListRepository.data[1] = new Todolist("Belajar Java OOP");
        todoListRepository.data[2] = new Todolist("Belajar Java Standard Clasess");

        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);

        todoListService.showTodolist();
    }

    public static void testAddTodolist() {
        TodoListRepository todoListRepository = new TodoListRepositoryImpl();
        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);

        todoListService.addTodolist("Belajar Java Dasar");
        todoListService.addTodolist("Belajar Java OOP");
        todoListService.addTodolist("Belajar Java Standard Clasess");

        todoListService.showTodolist();
    }

    public static void testRemoveTodolist() {
        TodoListRepository todoListRepository = new TodoListRepositoryImpl();
        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);

        todoListService.addTodolist("Belajar Java Dasar");
        todoListService.addTodolist("Belajar Java OOP");
        todoListService.addTodolist("Belajar Java Standard Clasess");
        todoListService.showTodolist();

        todoListService.removeTodolist(5);
        todoListService.removeTodolist(2);
        todoListService.showTodolist();

        todoListService.removeTodolist(2);
        todoListService.showTodolist();

        todoListService.removeTodolist(2);
        todoListService.showTodolist();

        todoListService.removeTodolist(1);
        todoListService.showTodolist();
    }
}
