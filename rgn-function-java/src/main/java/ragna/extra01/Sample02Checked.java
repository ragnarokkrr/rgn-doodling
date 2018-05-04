package ragna.extra01;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Sample02Checked {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(3, 9, 7, 6, 10, 20);

        integers.forEach(i -> {
            try {
                writeToFile(i);
            } catch (IOException e) {
                throw  new RuntimeException();
            }
        });


        integers.forEach(throwingConsumerWrapper(i->writeToFile(i)));

        integers.forEach(handlingConsumerWrapper(i->writeToFile(i), IOException.class));
    }


    static <T> Consumer<T> throwingConsumerWrapper(ThrowingConsumer<T, Exception> throwingConsumer) {
        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        };

    }



    static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
        return i -> {
          try {
              throwingConsumer.accept(i);
          } catch (Exception ex) {
              try {
                  E exCast = exceptionClass.cast(ex);
                  System.err.println("Exception occured: " + exCast.getMessage());

              } catch (ClassCastException ccex) {
                  throw new RuntimeException();
              }
          }
        };
    }

    static void writeToFile(Integer i) throws IOException {
        System.out.println(i);
    }
}




@FunctionalInterface
interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}

