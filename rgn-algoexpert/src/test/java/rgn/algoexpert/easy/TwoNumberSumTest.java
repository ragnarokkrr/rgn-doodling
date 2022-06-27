package rgn.algoexpert.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TwoNumberSumTest {

  @ParameterizedTest
  @MethodSource("provideValuesForTest")
  void testTwoNumberSum(int[] array, int targetSum, int[] answer) {
    final var twoNumberSum = TwoNumberSum.twoNumberSum(array, targetSum);

    assertThat(twoNumberSum).containsExactlyInAnyOrder(answer);
  }

  private static Stream<Arguments> provideValuesForTest() {
    return Stream.of(
        Arguments.of(new int[] {3, 5, -4, 8, 11, 1, -1, 6}, 10, new int[] {-1, 11}),
        Arguments.of(new int[] {4, 6}, 10, new int[] {4, 6}));
  }
}
