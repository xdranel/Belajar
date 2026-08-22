public class Standard_Class {
/*

========== LIST OF LESSON ==========
START OF INDEX

# 1. Introduction
Pastikan baca materi java Standard_Class di folder Materi/

==== String ====
# 2. String
# 3. StringBuffer and StringBuilder
# 4. StringJoiner
# 5. StringTokenizer

==== Number ====
# 6. Number
# 7. Math
# 8. BigNumber

# 9. Scanner
# 10. Date and Calender
# 11. System
# 12. Runtime
# 13. UUID
# 14. Base64
# 15. Objects
# 16. Random
# 17. Properties
# 18. Arrays
# 19. Regex

END OF INDEX
====================================
START OF SECTION 1

# 1. Introduction #

-. Baca File materi di foleder Materi/


END OF SECTION 1
====================================
START OF SECTION 2

# 2. String #

@@ Look Up File StringApp.java


END OF SECTION 2
====================================
START OF SECTION 3

# 3. StringBuffer and StringBuilder #

@@ Look Up File StringBuilderApp.java


END OF SECTION 3
====================================
START OF SECTION 4

# 4. StringJoiner #

@@ Look Up File StringJoinerApp.java


END OF SECTION 4
====================================
START OF SECTION 5

# 5. StringTokenizer #

@@ Look Up File StringTokenizerApp.java


END OF SECTION 5
====================================
START OF SECTION 6

# 6. Number #

@@ Look Up File NumberApp.java


END OF SECTION 6
====================================
START OF SECTION 7

# 7. Math #

@@ Look Up File MathApp.java


END OF SECTION 7
====================================
START OF SECTION 8

# 8. BigNumber #

@@ Look Up File BigNumberApp.java


END OF SECTION 8
====================================
START OF SECTION 9

# 9. Scanner #

@@ Look Up File ScannerApp.java


END OF SECTION 9
====================================
START OF SECTION 10

# 10. Date and Calender #

@@ Look Up File DateApp.java


END OF SECTION 10
====================================
START OF SECTION 11

# 11. System #

@@ Look Up File SystemApp.java


END OF SECTION 11
====================================
START OF SECTION 12

# 12. Runtime #

@@ Look Up File RuntimeApp.java


END OF SECTION 12
====================================
START OF SECTION 13

# 13. UUID #

@@ Look Up File UUIDApp.java


END OF SECTION 13
====================================
START OF SECTION 14

# 14. Base64 #

@@ Look Up File Base64App.java


END OF SECTION 14
====================================
START OF SECTION 15

# 15. Objects #

@@ Look Up File ObjectsApp.java


END OF SECTION 15
====================================
START OF SECTION 16

# 16. Random #

@@ Look Up File RandomApp.java


END OF SECTION 16
====================================
START OF SECTION 17

# 17. Properties #

@@ Look Up File PropertiesApp.java, application.properties, comment.properties


END OF SECTION 17
====================================
START OF SECTION 18

# 18. Arrays #

@@ Look Up File ArraysApp.java


END OF SECTION 18
====================================
START OF SECTION 19

# 19. Regex #

@@ Look Up File RegexApp.java


END OF SECTION 19

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailValidationApp {

    // Simple email regex pattern
    private static final String SIMPLE_EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // Complex email regex pattern
    private static final String COMPLEX_EMAIL_REGEX = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?=.{1,64})(?:(?![_.-])[a-zA-Z0-9._%+-]+(?:(?<![_.-])|(?=[_.-]))?)+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean validateEmailSimple(String email) {
        Pattern pattern = Pattern.compile(SIMPLE_EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateEmailComplex(String email) {
        Pattern pattern = Pattern.compile(COMPLEX_EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Email Validation App");
        System.out.println("====================");
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        System.out.println("\nValidation Results:");
        System.out.println("Simple Regex Valid: " + validateEmailSimple(email));
        System.out.println("Complex Regex Valid: " + validateEmailComplex(email));

        scanner.close();
    }
}

*/
}
