import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Set 테스트")
public class SetTest {
	private Set<Integer> numbers;

	// @BeforeEach vs @BeforeAll
	// https://junit.org/junit5/docs/5.0.2/api/org/junit/jupiter/api/BeforeEach.html
	// This will run before every test, so it can reset the conditions for the next one.
	@BeforeEach
	void setUp() {
		numbers = new HashSet<>();
		numbers.add(1);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
	}

	@Test
	@DisplayName("Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.")
	public void givenSet_whenGetSize_thenReturnCorrentResult() {
		assertThat(numbers.size()).isEqualTo(3);
	}

	// https://www.baeldung.com/parameterized-tests-junit-5
	// https://nipafx.dev/junit-5-parameterized-tests/
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	@DisplayName("Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트를 구현한다.")
	public void givenElement_whenContainsElement_thenReturnTrue(int number) {
		assertThat(numbers.contains(number)).isTrue();
	}

	static Stream<Arguments> arguments = Stream.of(
		Arguments.of(1, true),
		Arguments.of(2, true),
		Arguments.of(3, true),

		Arguments.of(true, false),
		Arguments.of(4, false),
		Arguments.of(5, false),
		Arguments.of("hi", false)
	);

	// 원래 CSV로 하다가, 타입이 다른 문제가 있어서 위 방식으로 함...
	@ParameterizedTest
	@VariableSource("arguments")
	@DisplayName("입력 값에 따라 결과 값이 다른 경우에 대한 테스트도 가능하도록 구현한다.")
	public void givenElement_whenContainsWrongElement_thenReturnFalse(Object input, boolean expected) {
		assertThat(numbers.contains(input)).isEqualTo(expected);
	}
}
