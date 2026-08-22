public class TipeDataNumber {
    public static void main(String[] args) {

        byte iniByte = 100;
        short iniShort = 1000;
        int iniInt = 10000000;
        long iniLong = 1000000000;
        long iniLong2 = 1000000000L;

        float iniFloat = 1.120f;
        double iniDouble = 1.220;

        int decimalInt = 30;
        int hexadecimal = 0xFFFFFF;
        int binaryDecimal = 0b00110010;

        int balance = 1_000_000;

//      Konversi Tipe Data
        byte iniByte2 = 10;
        short iniShort2 = iniByte2;
        int iniInt2 = iniShort2;

        byte iniByte3 = (byte) iniInt2;
    }
}
