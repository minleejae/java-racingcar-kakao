package racingcar.model;

import racingcar.generator.NumberGenerator;
import racingcar.generator.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private final List<RacingCar> racingCars;
    private final int totalRounds;
    private int currentRound = 0;

    public RacingGame(List<String> carNames, int totalRounds) {
        this(carNames, totalRounds, new RandomNumberGenerator());
    }

    public RacingGame(List<String> carNames, int totalRounds, NumberGenerator numberGenerator) {
        this.totalRounds = totalRounds;
        this.racingCars = carNames.stream()
                .map(name -> new RacingCar(name, numberGenerator))
                .collect(Collectors.toList());
    }

    public void performRacingRound() {
        racingCars.forEach(RacingCar::move);
        currentRound++;
    }

    public List<RacingCar> getRacingCars() {
        return new ArrayList<>(racingCars);
    }

    public List<RacingCar> findWinners() {
        int maxMoves = racingCars.stream()
                .mapToInt(RacingCar::getMoves)
                .max()
                .orElse(0);

        return racingCars.stream()
                .filter(car -> car.isAt(maxMoves))
                .collect(Collectors.toList());
    }

    public boolean isGameNotOver() {
        return currentRound < totalRounds;
    }
}
