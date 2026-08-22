public class IfStatement {
    public static void main(String[] args) {

        var nilai = 70;
        var absen = 90;

        if (nilai >= 80 && absen >= 80){
            System.out.println("Your Grade is A");
        } else if (nilai >= 70 && absen >= 70) {
            System.out.println("Your Grade is B");
        } else if (nilai >= 60 && absen >= 60) {
            System.out.println("Your Grade is C");
        }  else if (nilai >= 50 && absen >= 50) {
            System.out.println("Your Grade is D");
        } else {
            System.out.println("Your Grade is E");
        }
    }
}
