package belajar.java;

import java.math.BigInteger;

public class BigNumberApp {
    public static void main(String[] args) {

//        Long val = 10000000000000000000L; Error Exceeding Long
        BigInteger bi = new BigInteger("10000000000000000000");
        BigInteger bi2 = new BigInteger("20000000000000000000");

        BigInteger bi3 = bi.add(bi2);
        System.out.println(bi3);

        System.out.println(bi3.subtract(bi));
    }
}
