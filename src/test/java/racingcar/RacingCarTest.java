package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.generator.RandomNumberGenerator;
import racingcar.model.RacingCar;
import racingcar.model.RacingGame;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RacingCarTest {
    private static final int ONE_ROUND = 1;
    private static final int IS_MOVED = 1;
    private static final int NOT_MOVED = 0;
    private static final int ONE_MOVED = 1;
    private static final int TWO_MOVED = 2;
    private static final int WINNERS_NUMBER_FOR_WINNING_TEST = 2;
    private static final List<Integer> MOVES_FOR_WINNING_TEST = Arrays.asList(9, 2, 8);
    private static final List<Integer> MOVES_FOR_ROUND_TEST = Arrays.asList(4, 5, 6);

    private static final List<Integer> NON_MOVES_TRIGGERING_NUMBERS = Arrays.asList(3, 10);
    private static final List<Integer> MOVES_TRIGGERING_NUMBERS = Arrays.asList(4, 9);
    private RandomNumberGenerator randomNumberGenerator;

    @BeforeEach
    void setUp() {
        randomNumberGenerator = new RandomNumberGenerator();
    }

    @Test
    void moveTest() {
        RacingCar car = new RacingCar("car", new TestNumberGenerator(MOVES_TRIGGERING_NUMBERS));
        car.move();
        assertTrue(car.isAt(ONE_MOVED), "자동차가 한 번 움직인 후의 상태를 검증합니다.");
        car.move();
        assertTrue(car.isAt(TWO_MOVED), "자동차가 두 번 움직인 후의 상태를 검증합니다.");
    }

    @Test
    void stopTest() {
        RacingCar car = new RacingCar("car", new TestNumberGenerator(NON_MOVES_TRIGGERING_NUMBERS));
        car.move();
        assertTrue(car.isAt(NOT_MOVED), "자동차가 움직이지 않은 후의 상태를 검증합니다.");
        car.move();
        assertTrue(car.isAt(NOT_MOVED), "자동차가 다시 한 번 움직이지 않은 후의 상태를 검증합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "123", "1234", "12345"})
    void whenNameIsValid_thenDoesNotThrowException(String input) {
        assertDoesNotThrow(() -> new RacingCar(input, randomNumberGenerator));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"123456"})
    void whenNameIsInvalid_thenThrowsException(String input) {
        assertThrows(IllegalArgumentException.class, () -> new RacingCar(input, randomNumberGenerator));
    }

    @Test
    void determineWinnerTest() {
        List<String> carNames = Arrays.asList("car1", "car2", "car3");
        RacingGame racingGame = new RacingGame(carNames, ONE_ROUND, new TestNumberGenerator(MOVES_FOR_WINNING_TEST));

        racingGame.performRacingRound();

        List<RacingCar> winners = racingGame.findWinners();

        assertEquals(WINNERS_NUMBER_FOR_WINNING_TEST, winners.size());
        assertEquals("car1", winners.get(0).getName());
        assertEquals("car3", winners.get(1).getName());
    }

    @Test
    public void testPerformRacingRound() {
        List<String> carNames = Arrays.asList("car1", "car2", "car3");
        RacingGame racingGame = new RacingGame(carNames, ONE_ROUND, new TestNumberGenerator(MOVES_FOR_ROUND_TEST));

        racingGame.performRacingRound();

        List<RacingCar> racingCars = racingGame.getRacingCars();
        for (RacingCar car : racingCars) {
            assertEquals(IS_MOVED, car.getMoves());
        }
    }
}
