import java.util.Scanner;

public class AplikasiTodoList {
    public static String[] model = new String[10];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        viewShowTodoList();
    }

    /**
     * Show TodoList
     */
    public static void showTodoList() {
        System.out.println("TODOLIST");
        for (int i = 0; i < model.length; i++) {
            var todo = model[i];
            var number = i + 1;

            if (todo != null) {
                System.out.println(number + ". " + todo);
            }
        }
    }

    public static void testShowTodoList() {
        model[0] = "Java Dasar";
        model[1] = "Java OOP";
        model[2] = "Java Generic";
        showTodoList();
    }

    /**
     * Add TodoList
     */
    public static void addTodoList(String todo) {

        var isFull = true;
        for (int i = 0; i < model.length; i++) {
            if (model[i] == null) {
                isFull = false;
                break;
            }
        }

        if (isFull) {
            var temp = model;
            model = new String[model.length * 2];

            for (int i = 0; i < temp.length; i++) {
                model[i] = temp[i];
            }
        }


        for (int i = 0; i < model.length; i++) {
            if (model[i] == null) {
                model[i] = todo;
                break;
            }
        }
    }

    public static void testAddTodoList() {
        for (int i = 0; i <= 25; i++) {
            addTodoList("Number-" + i);
        }

        showTodoList();
    }

    /**
     * Remove TodoList
     */
    public static boolean removeTodoList(Integer number) {
        if ((number - 1) >= model.length) {
            return false;
        } else if (model[number - 1] == null) {
            return false;
        } else {
            for (int i = (number - 1); i < model.length; i++) {
                if (i == model.length - 1) {
                    model[i] = null;
                } else {
                    model[i] = model[i + 1];
                }
            }
            return true;
        }
    }

    public static void testRemoveTodoList() {
        addTodoList("One");
        addTodoList("Two");
        addTodoList("Three");
        addTodoList("Four");
        addTodoList("Five");

        var result = removeTodoList(11);
        System.out.println(result);

        result = removeTodoList(6);
        System.out.println(result);

        result = removeTodoList(2);
        System.out.println(result);

        showTodoList();
    }

    /**
     * Input TodoList
     */
    public static String input(String prompt) {
        System.out.print(prompt + " : ");
        String data = scanner.nextLine();
        return data;
    }

    public static void testInput() {
        var name = input("Enter Name");
        System.out.println("Hi " + name);

        var age = input("Enter Age");
        System.out.println("You are " + age + " years old");
    }

    /**
     * Show TodoList Menu
     */
    public static void viewShowTodoList() {
        while (true) {
            showTodoList();

            System.out.println("Menu : ");
            System.out.println("1. Add");
            System.out.println("2. Remove");
            System.out.println("x. Exit");

            var input = input("Enter Choice").toLowerCase();
            if (input.equals("1")) {
                viewAddTodoList();
            } else if (input.equals("2")) {
                viewRemoveTodoList();
            } else if (input.equals("x")) {
                break;
            } else {
                System.out.println("Invalid Choice");
            }
        }
    }

    public static void testViewShowTodoList() {
        addTodoList("One");
        addTodoList("Two");
        addTodoList("Three");
        addTodoList("Four");
        addTodoList("Five");
        viewShowTodoList();
    }

    /**
     * Show Add TodoList Menu
     */
    public static void viewAddTodoList() {
        System.out.println("Adding TODOLIST");

        var todo = input("Todo (x for cancel)");
        if (todo.equals("x") || todo.equals("X")) {

        } else {
            addTodoList(todo);
        }
    }

    public static void testViewAddTodoList() {
        addTodoList("One");
        addTodoList("Two");

        viewAddTodoList();

        showTodoList();
    }

    /**
     * Show Remove TodoList Menu
     */
    public static void viewRemoveTodoList() {
        System.out.println("REMOVING TODOLIST");

        var number = input("What Todo Number Remove? (x for cancel)");

        if (number.equals("x") || number.equals("X")) {

        } else {
            boolean success = removeTodoList(Integer.valueOf(number));
            if (!success) {
                System.out.println("Failed to remove Todo : " + number);
            }
        }
    }

    public static void testViewRemoveTodoList() {
        addTodoList("One");
        addTodoList("Two");
        addTodoList("Three");

        showTodoList();

        viewRemoveTodoList();

        showTodoList();
    }
}
