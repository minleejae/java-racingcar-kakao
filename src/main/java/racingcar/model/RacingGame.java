package racingcar.model;

import racingcar.generator.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private final List<RacingCar> racingCars;

    public RacingGame(List<String> carNames) {
        this.racingCars = carNames.stream()
                .map(RacingCar::new)
                .collect(Collectors.toList());
    }

    public RacingGame(List<String> carNames, NumberGenerator numberGenerator) {
        this.racingCars = carNames.stream()
                .map(name -> new RacingCar(name, numberGenerator))
                .collect(Collectors.toList());
    }

    public void performRacingRound() {
        racingCars.forEach(RacingCar::move);
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
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
}
