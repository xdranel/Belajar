public class MethodReturnValue {
    public static void main(String[] args) {

        var result = sum(10, 20);

        System.out.println(result);
        System.out.println(sum(10, 50));

        System.out.println(simpleCalculator(10, "*", 20));
    }

    static int sum(int a, int b) {
        var sum = a + b;
        return sum;
    }

    static int simpleCalculator(int a, String operation, int b) {
        switch (operation) {
            case "add", "+":
                return a + b;
            case "sub", "-":
                return a - b;
            case "mul", "*":
                return a * b;
            case "div", "/":
                return a / b;
            default:
                return 0;
        }
    }
}
