package ragna.c06;

import java.util.function.Supplier;

public class Evaluation {

    public static boolean evaluate(final int value) {
        System.out.println("evaluating ...." + value);
        simulateTimeConsumingOp(2000);
        return value > 100;
    }

    public static void eagerEvaluator(final boolean input1, final boolean input2) {
        System.out.println("eagerEvaluator called...");
        System.out.println("accept?: " + (input1 && input2));
    }

    public static void lazyEvaluator(final Supplier<Boolean> input1, final Supplier<Boolean> input2) {
        System.out.println("lazyEvaluator called..");

        System.out.println("accerot?: " + (input1.get() && input2.get()));
    }

    private static void simulateTimeConsumingOp(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        eagerEvaluator(evaluate(1), evaluate(2));
        lazyEvaluator(() -> evaluate(1), () -> evaluate(2));

    }
}
