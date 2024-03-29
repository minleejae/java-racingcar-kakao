package racingcar.generator;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int UPPER_BOUND = 10;
    private final Random random = new Random();

    @Override
    public int generate() {
        return random.nextInt(UPPER_BOUND);
    }
}

