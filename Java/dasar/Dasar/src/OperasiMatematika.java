public class OperasiMatematika {
    public static void main(String[] args) {

        int a = 100;
        int b = 10;

        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b);
        System.out.println(a % b);

//      Augmented Assignments
        int c = 100;
        c = c + 10;
        System.out.println(c);
        c = c - 10;
        System.out.println(c);
        c *= 10;
        System.out.println(c);
        c /= 10;
        System.out.println(c);
        c %= 10;
        System.out.println(c);

//      Unary Operator
        int d = 10;
        int e = -10;
        d++;
        System.out.println(d);
        d--;
        System.out.println(d);
        System.out.println(e);
        System.out.println(!true);

    }
}
