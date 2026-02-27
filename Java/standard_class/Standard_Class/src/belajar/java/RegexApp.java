package belajar.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexApp {
    public static void main(String[] args) {

        String name = "Felix Immanuel Studying Java Language";

//        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
//        Pattern pattern = Pattern.compile("^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?=.{1,64})(?:(?![_.-])[a-zA-Z0-9._%+-]+(?:(?<![_.-])|(?=[_.-]))?)+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        Pattern pattern = Pattern.compile("[a-zA-Z]*[a][a-zA-Z]*");
        Matcher matcher = pattern.matcher(name);

        while (matcher.find()) {
//            System.out.println(matcher.group());
            String result = matcher.group();
            System.out.println(result);
        }
    }
}
