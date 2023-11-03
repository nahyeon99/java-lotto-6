package lotto.domain;

import java.util.List;
import lotto.view.InputView;

public class Lotto {
    private static final String SIZE_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개를 입력해야 합니다.";
    private static final String RANGE_ERROR_MESSAGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "[ERROR] 로또 번호는 중복 없이 입력해야 합니다.";

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_COUNT = 6;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        numbers.forEach(number -> InputView.validateBlank(String.valueOf(number)));
        numbers.forEach(InputView::validateNegativeInteger);
        validateSize(numbers.size());
        validateRange(numbers);
        validateDuplicate(numbers);

        this.numbers = numbers.stream().toList();
    }

    public static Lotto of(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validateSize(int size) {
        if (size != LOTTO_COUNT) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    private void validateRange(List<Integer> numbers) {
        numbers.forEach(number -> {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(RANGE_ERROR_MESSAGE);
            }
        });
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }
}
