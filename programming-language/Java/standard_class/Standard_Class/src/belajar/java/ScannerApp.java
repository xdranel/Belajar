package belajar.java;

import java.util.Scanner;

public class ScannerApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan nama: ");
        String nama = sc.nextLine();
        System.out.print("Masukkan umur: ");
        Integer umur = sc.nextInt();

        System.out.println("Halo " + nama + " Umurmu: " + umur);
    }
}
