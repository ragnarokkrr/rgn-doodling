package ragna.c04;

import java.awt.Color;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Camera {

    private Function<Color, Color> filter;

    public Camera() {
        setFilters();
    }

    public void setFilters(final Function<Color, Color>... filters) {
        filter = Arrays.asList(filters).stream()
                .reduce((filter, next)->
                        filter.compose(next))
                .orElse(color -> color);
    }

    public Color capture (final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);

        return processedColor;
    }

    public static void main(String[] args) {
        final Camera camera = new Camera();

        final Consumer<String> printCaptured = (filterInfo) ->
                System.out.println(String.format("with %s: %s", filterInfo,
                        camera.capture(new Color(200, 100, 200))));

        printCaptured.accept("no filter");

        camera.setFilters(Color::brighter);
        printCaptured.accept("darker filter");

        camera.setFilters(Color::brighter, Color::darker);
        printCaptured.accept("brighter & darker filters ");

        camera.setFilters(Color::brighter, Color::darker, Color::darker);
        printCaptured.accept("brighter & darker & darker filters ");

        Function<Color, Color> customFilterMinus10 = (color) ->
                new Color(color.getRed() -10, color.getGreen() -10, color.getBlue()-10);

        camera.setFilters(Color::brighter, Color::darker, Color::darker, customFilterMinus10);
        printCaptured.accept("brighter & darker & darker & minus10 filters ");

    }
}
