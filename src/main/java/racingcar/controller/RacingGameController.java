package racingcar.controller;

import racingcar.model.RacingGame;
import racingcar.view.RacingGameView;

import java.util.List;

public class RacingGameController {
    RacingGameView racingGameView;

    public RacingGameController(RacingGameView racingGameView) {
        this.racingGameView = racingGameView;
    }

    public void playGame() {
        RacingGame racingGame = initializeRacingGame();

        racingGameView.startGameRound();

        while (racingGame.isGameNotOver()) {
            racingGame.performRacingRound();
            racingGameView.displayRacingCarStatus(racingGame.getRacingCars());
        }

        racingGameView.displayWinners(racingGame.findWinners());
    }

    private RacingGame initializeRacingGame() {
        try {
            int trial = racingGameView.requestTrial();
            List<String> carNames = racingGameView.requestCarNames();
            return new RacingGame(carNames, trial);
        } catch (IllegalArgumentException e) {
            racingGameView.displayError(e.getMessage());
            return initializeRacingGame();
        }
    }
}
