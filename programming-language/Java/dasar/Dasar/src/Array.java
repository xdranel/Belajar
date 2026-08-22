public class Array {
    public static void main(String[] args) {

        String[] stringArray = new String[3];
        stringArray[0] = "Gendhi";
        stringArray[1] = "Ramona";
        stringArray[2] = "Prastyo";

        System.out.println(stringArray[0]);
        System.out.println(stringArray[1]);
        System.out.println(stringArray[2]);

        stringArray[0] = "Felix";
        System.out.println(stringArray[0]);

//      Array Initializer
        String[] namaNama = {
                "Gendhi", "Ramona", "Prastyo"
        };

        int[] arrayInt = new int[]{
                10, 20, 30, 40, 50
        };

        long[] arrayLong = {
                10L, 20L, 30L, 40L, 50L
        };

//      Accesing Length
        System.out.println(namaNama.length);

//      Array Inside Array
        String[][] members = {
                {"Gendhi", "Ramona", "Prastyo"},
                {"Felix", "Kjelberg"},
                {"Budi", "Kurniawan"},
        };
        System.out.println(members[0][1]);
        System.out.println(members[1][0]);
        System.out.println(members[2][1]);
    }
}
