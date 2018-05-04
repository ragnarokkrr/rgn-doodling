package ragna.extra01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Sample01Unchecked {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(3, 9, 7, 6, 10, 20);
        integers.forEach(i -> System.out.println(50/i));


        integers.forEach(i -> {
            try {
                System.out.println(50 / i);
            } catch (ArithmeticException e ) {
                System.err.println("Arithmetic excetption occured: "  + e.getMessage());
            }
        });

        integers.forEach(lambdaWrapper(i -> System.out.println(50/i)));

        integers.forEach(consumerWrapper(i-> System.out.println(50/i), ArithmeticException.class));
    }


    static Consumer<Integer> lambdaWrapper(Consumer<Integer> consumer) {
        return i -> {
          try{
              consumer.accept(i);
          } catch (ArithmeticException e) {
              System.err.println("Arithmetic Exception occured: " + e.getMessage());
          }
        };
    }

    static <T, E extends Exception> Consumer<T> consumerWrapper(Consumer<T> consumer, Class<E> clazz) {
        return i -> {
          try {
              consumer.accept(i);
          } catch (Exception ex) {
              try {
                  E exCast = clazz.cast(ex);
                  System.err.println("Exception occured" + exCast.getMessage());
              } catch (ClassCastException ccex) {
                  throw ex;
              }
          }
        };
    }

}
