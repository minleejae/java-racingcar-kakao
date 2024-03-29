package racingcar.controller;

import racingcar.exception.InValidInputException;
import racingcar.model.RacingGame;
import racingcar.view.RacingGameView;

import java.util.List;

public class RacingGameController {
    RacingGameView racingGameView;

    public RacingGameController(RacingGameView racingGameView) {
        this.racingGameView = racingGameView;
    }

    public void playGame() {
        int trial = requestTrial();

        RacingGame racingGame = initializeRacingGame();

        racingGameView.startGameRound();

        for (int i = 0; i < trial; i++) {
            racingGame.performRacingRound();
            racingGameView.displayRacingCarStatus(racingGame.getRacingCars());
        }

        racingGameView.displayWinners(racingGame.findWinners());
    }

    private int requestTrial() {
        try {
            return racingGameView.requestTrial();
        } catch (InValidInputException e) {
            System.out.println(e.getMessage());
            return requestTrial();
        }
    }

    private List<String> requestCarNames() {
        try {
            return racingGameView.requestCarNames();
        } catch (InValidInputException e) {
            System.out.println(e.getMessage());
            return requestCarNames();
        }
    }

    private RacingGame initializeRacingGame() {
        List<String> carNames = requestCarNames();
        return new RacingGame(carNames);
    }
}
