package racingcar.model;

import racingcar.generator.NumberGenerator;

public class RacingCar {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_MOVE_THRESHOLD = 4;
    private static final int MAX_MOVE_THRESHOLD = 9;
    private final NumberGenerator numberGenerator;
    private final String name;
    private int moves = 0;

    public RacingCar(String name, NumberGenerator numberGenerator) {
        validateName(name);
        this.name = name;
        this.numberGenerator = numberGenerator;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름이 적절하지 않습니다.");
        }
    }

    public void move() {
        int num = numberGenerator.generate();
        getNumAndMove(num);
    }

    private void getNumAndMove(int num) {
        if (isMove(num)) {
            this.moves++;
        }
    }

    private boolean isMove(int num) {
        return num >= MIN_MOVE_THRESHOLD && num <= MAX_MOVE_THRESHOLD;
    }

    public String getName() {
        return name;
    }

    public int getMoves() {
        return moves;
    }

    public boolean isAt(int moves) {
        return this.moves == moves;
    }
}
