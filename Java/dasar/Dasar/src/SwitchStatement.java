public class SwitchStatement {
    public static void main(String[] args) {

        var grade = "F";

        switch (grade) {
            case "A":
                System.out.println("You Passing The Grade With Excellent Grade");
                break;
            case "B":
            case "C":
                System.out.println("You Passing The Grade With Ok Grade");
                break;
            case "D":
                System.out.println("You're Not Passing The Grade");
                break;
            default:
                System.out.println("Maybe You Got Into Wrong Major");
                break;
        }

//      Switch Lambda (Java 14)
        switch (grade) {
            case "A" -> System.out.println("You Passing The Grade With Excellent Grade");
            case "B", "C" -> System.out.println("You Passing The Grade With Ok Grade");
            case "D" -> System.out.println("You're Not Passing The Grade");
            default -> System.out.println("Maybe You Got Into Wrong Major");
        }

//      Yield (Java 14)
//      Without Yield
        String massage;
        switch (grade) {
            case "A" -> massage = "You Passing The Grade With Excellent Grade";
            case "B", "C" -> massage = "You Passing The Grade With Ok Grade";
            case "D" -> massage = "You're Not Passing The Grade";
            default -> massage = "Maybe You Got Into Wrong Major";
        }
        System.out.println(massage);

//      With Yield
        massage = switch (grade) {
            case "A":
                yield "You Passing The Grade With Excellent Grade";
            case "B", "C":
                yield "You Passing The Grade With Ok Grade";
            case "D":
                yield "You're Not Passing The Grade";
            default:
                yield "Maybe You Got Into Wrong Major";
        };
        System.out.println(massage);
    }
}
