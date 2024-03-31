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
        validateCarNames(carNames);
        validateTotalRounds(totalRounds);
        validateNumberGenerator(numberGenerator);

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

    private void validateCarNames(List<String> carNames) {
        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR]차량 이름은 null이거나 비어 있으면 안 됩니다.");
        }
        if (carNames.stream().anyMatch(name -> name == null || name.trim().isEmpty())) {
            throw new IllegalArgumentException("[ERROR]각 차량 이름은 null이거나 공백일 수 없습니다.");
        }
    }

    private void validateTotalRounds(int totalRounds) {
        if (totalRounds <= 0) {
            throw new IllegalArgumentException("[ERROR]시도할 회수는 0보다 커야 합니다.");
        }
    }

    private void validateNumberGenerator(NumberGenerator numberGenerator) {
        if (numberGenerator == null) {
            throw new IllegalArgumentException("[ERROR]NumberGenerator는 null일 수 없습니다.");
        }
    }
}
