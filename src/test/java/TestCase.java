import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

public class TestCase implements Arguments {

	private final Object input;
	private final Object expected;

	public Object getInput() {
		return this.input;
	}

	public Object getExpected() {
		return this.expected;
	}

	protected TestCase(Object input, Object expected) {
		this.input = input;
		this.expected = expected;
	}

	@Override
	public Object[] get() {
		return new Object[]{this.input, this.expected};
	}

	// -------------- Builder chaining --------------

	// https://www.geeksforgeeks.org/anonymous-inner-class-java/
	protected interface CaseChainable {
		TestCase expected(Object output);
	}

	// TestCase.given(...).expected(...);
	static CaseChainable given(Object input) {
		return new CaseChainable() {
			public TestCase expected(Object output) {
				return new TestCase(input, output);
			}
		};
	}

}
