package racingcar;

import org.junit.jupiter.api.Test;
import racingcar.model.RacingCar;
import racingcar.model.RacingGame;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RacingGameTest {
    private static final int ONE_ROUND = 1;
    private static final int IS_MOVED = 1;
    private static final int WINNERS_NUMBER_FOR_WINNING_TEST = 2;
    private static final List<Integer> MOVES_FOR_WINNING_TEST = Arrays.asList(9, 2, 8);
    private static final List<Integer> MOVES_FOR_ROUND_TEST = Arrays.asList(4, 5, 6);

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
