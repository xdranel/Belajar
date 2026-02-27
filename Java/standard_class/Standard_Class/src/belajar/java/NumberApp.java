package belajar.java;

public class NumberApp {
    public static void main(String[] args) {

        Integer intvalue = 10;

        Long longValue = intvalue.longValue();
        Double doubleValue = longValue.doubleValue();
        Short shortValue = doubleValue.shortValue();

        String example = "100.10";

//        int intExample = Integer.parseInt(example);
//        System.out.println(intExample);

        double doubleExample = Double.parseDouble(example);
        System.out.println(doubleExample);

        Double doubleExample2 = Double.valueOf(example);
        System.out.println(doubleExample2);
    }
}
