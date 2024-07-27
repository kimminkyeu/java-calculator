package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {

	private static final Pattern CUSTOM_DELIM_PATTERN = Pattern.compile( "(//)(.*)(\\n)" );

	public Integer sum(String expression) {
		List<Integer> numbers = this.convertToPositiveNumbers( this.split(expression) );
		return numbers.stream().reduce(0, Integer::sum);
	}

	Predicate<Integer> isNegative = i -> (i < 0);
	Predicate<Integer> isPositive = i -> (0 <= i);

	// +3은 되겠지..
	private List<Integer> convertToPositiveNumbers(String[] inputValues) {

		List<Integer> numbers;

		if (inputValues.length == 1 && inputValues[0].isEmpty()) {
			return List.of(0);
		}

		try {
			// NOTE: https://www.baeldung.com/java-8-collectors
			numbers = Arrays.stream(inputValues).map(Integer::parseInt).collect(Collectors.toList());

		} catch (NumberFormatException e) {
			throw new RuntimeException(
				String.format("%s is not a valid argument", e.getMessage())
			);
		}

		List<Integer> negativeNumbers = numbers.stream().filter(isNegative).collect(Collectors.toList());
		if (!negativeNumbers.isEmpty()) {
			throw new RuntimeException(
				String.format("[%s] : calculator can't handle negative value", negativeNumbers)
			);
		}

		return numbers;
	}

	// https://www.baeldung.com/java-string-split-multiple-delimiters
	private String[] split(String input) {

		List<String> delimiters = new ArrayList<String>(
			Arrays.asList(",", ":", " ")
		);
		String refinedInput = input;

		// get custom delim
		Matcher matcher = CUSTOM_DELIM_PATTERN.matcher(refinedInput);
		if (matcher.find()) {
			delimiters.add(matcher.group(2)); // get custom delimiter
			refinedInput = input.replace(matcher.group(0), ""); // remove "//..?..\n" from input
		}

		// regex "OR"
		String splitRegex = String.format("(%s)", String.join("|", delimiters));
		return refinedInput.split(splitRegex);
	}
}
