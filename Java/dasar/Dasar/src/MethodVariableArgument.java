public class MethodVariableArgument {
    public static void main(String[] args) {

        int[] values = {70, 90, 85, 50, 40};
        sayCongrats("Felix", values);

        sayCongrats("Daniel", 70,80,60,95,90);
    }

    static void sayCongrats(String name, int... values) {
        var total = 0;
        for (var value : values) {
            total += value;
        }
        var result = total / values.length;

        if (result >= 75) {
            System.out.println("Congratulations! " + name + " You Pass");
        } else  {
            System.out.println("Sorry " + name + " You Failed");
        }
    }
}
