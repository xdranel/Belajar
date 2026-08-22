public class TipeDataBukanPrimitif {
    public static void main(String[] args) {

        Integer iniInteger = 100;
        Long iniLong = 10000L;
        Byte iniByte = null;

        System.out.println(iniByte);

        iniByte = 100;
        System.out.println(iniByte);

//      Konversi
        int iniInt = 50;
        Integer iniInteger2 = iniInt;
        System.out.println(iniInteger2);

//      Konversi ke tipe data primitf yang berbeda
        short iniShort = iniInteger2.shortValue();
        long iniLong2 = iniInteger2.longValue();
    }
}
