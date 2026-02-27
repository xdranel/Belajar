package test.view;

import repository.TodoListRepository;
import repository.TodoListRepositoryImpl;
import service.TodoListService;
import service.TodoListServiceImpl;
import view.TodoListView;

public class TodoListViewTest {
    public static void main(String[] args) {
        testRemoveTodolist();
    }

    public static void testShowTodolist() {
        TodoListRepository todoListRepository = new TodoListRepositoryImpl();
        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);
        TodoListView todoListView = new TodoListView(todoListService);

        todoListService.addTodolist("Belajar Java Dasar");
        todoListService.addTodolist("Belajar Java OOP");
        todoListService.addTodolist("Belajar Java Standard Classes");

        todoListView.showTodoList();
    }

    public static void testAddTodolist() {
        TodoListRepository todoListRepository = new TodoListRepositoryImpl();
        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);
        TodoListView todoListView = new TodoListView(todoListService);


        todoListView.addTodoList();

        todoListService.showTodolist();
    }

    public static void testRemoveTodolist() {
        TodoListRepository todoListRepository = new TodoListRepositoryImpl();
        TodoListService todoListService = new TodoListServiceImpl(todoListRepository);
        TodoListView todoListView = new TodoListView(todoListService);

        todoListService.addTodolist("Belajar Java Dasar");
        todoListService.addTodolist("Belajar Java OOP");
        todoListService.addTodolist("Belajar Java Standard Classes");

        todoListService.showTodolist();

        todoListView.removeTodoList();

        todoListService.showTodolist();
    }
}
