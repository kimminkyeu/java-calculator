import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import calculator.Calculator;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("계산기 요구 사항")
public class CalculatorTest {

	public Calculator calculator = new Calculator();

	static Stream<TestCase> calculatorSuccessTestCases = Stream.of(

		TestCase.given("").expected(0),

		TestCase.given("1,2").expected(3),
		TestCase.given("1,2,3").expected(6),
		TestCase.given("1,2:3").expected(6),
//		TestCase.given("  ++3,,  ;, +  +++4: :,:+5: :   ,  ").expected(12)

		TestCase.given("//;\n1;2;3").expected(6),
		TestCase.given("//add\n1add2add3").expected(6)
	);

	@ParameterizedTest
	@VariableSource("calculatorSuccessTestCases")
	@DisplayName("Sum Test")
	public void givenExpression_whenCalculate_thenReturnCorrectResult(
		String expression, int expected
	) {
		assertThat(calculator.sum(expression)).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"1,-1",
	})
	public void givenExpression_whenNegative_thenThrowRuntimeException(String expression) {
		assertThatExceptionOfType(RuntimeException.class)
			.isThrownBy(() -> calculator.sum(expression));
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"1,hi",
	})
	public void givenExpression_whenNaN_thenThrowRuntimeException(String expression) {
		assertThatExceptionOfType(RuntimeException.class)
			.isThrownBy(() -> calculator.sum(expression));
	}
}
