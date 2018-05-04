package ragna.c03;

public class IterateString {

    public static void main(String[] args) {
        final String str = "w00t";

        str.chars().forEach(ch -> System.out.println(ch));

        str.chars().forEach(System.out::println);

        str.chars().forEach(IterateString::printChar);

        str.chars()
                .mapToObj(ch -> Character.valueOf((char) ch))
                .forEach(System.out::println);
    }



    private static void printChar(int aChar) {
        System.out.println((char) aChar);
    }
}
