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
    private RacingCar racingCar;
    private RandomNumberGenerator randomNumberGenerator;

    @BeforeEach
    void setUp() {
        racingCar = new RacingCar();
        randomNumberGenerator = new RandomNumberGenerator();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void moveTest(int number) {
        assertTrue(racingCar.isMove(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void stopTest(int number) {
        assertFalse(racingCar.isMove(number));
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
        List<String> racingCarNames = Arrays.asList("car1", "car2", "car3");
        RacingGame racingGame = new RacingGame(racingCarNames, ONE_ROUND);

        racingGame.getRacingCars().get(0).getNumAndMove(9);
        racingGame.getRacingCars().get(1).getNumAndMove(2);
        racingGame.getRacingCars().get(2).getNumAndMove(8);

        List<RacingCar> winners = racingGame.findWinners();

        assertEquals(2, winners.size());
        assertEquals("car1", winners.get(0).getName());
        assertEquals("car3", winners.get(1).getName());
    }

    @Test
    public void testPerformRacingRound() {
        List<String> carNames = Arrays.asList("car1", "car2", "car3");
        RacingGame racingGame = new RacingGame(carNames, ONE_ROUND, new TestNumberGenerator(5));

        racingGame.performRacingRound();

        List<RacingCar> racingCars = racingGame.getRacingCars();
        for (RacingCar car : racingCars) {
            assertEquals(1, car.getMoves());
        }
    }
}
