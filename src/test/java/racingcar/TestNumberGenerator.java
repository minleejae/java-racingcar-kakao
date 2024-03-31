package racingcar;

import racingcar.generator.NumberGenerator;

import java.util.Iterator;
import java.util.List;

public class TestNumberGenerator implements NumberGenerator {
    private final Iterator<Integer> numbersIterator;

    public TestNumberGenerator(List<Integer> numbers) {
        this.numbersIterator = numbers.iterator();
    }

    @Override
    public int generate() {
        return numbersIterator.hasNext() ? numbersIterator.next() : 0;
    }
}
