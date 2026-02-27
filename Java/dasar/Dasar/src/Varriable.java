public class Varriable {
    public static void main(String[] args) {

        String name;
        name = "Gendhi Ramona";
        int age = 21;
        String address = "Indonesia";
        System.out.println(name);
        System.out.println(age);
        System.out.println(address);

        name = "Budi Kurniawan";
        System.out.println(name);

//      Using Var
        var name2 = "Eko Khannedy";
        var age2 = 26;
        var address2 = "Indonesia";
        System.out.println(name2);
        System.out.println(age2);
        System.out.println(address2);

//      Using Final
        final String name3 = "Felix Edwin";
        var age3 = 27;
        var address3 = "Indonesia";
        System.out.println(name3);
        System.out.println(age3);
        System.out.println(address3);

//        name3 = "Nama Diubah" <= Error
    }
}
