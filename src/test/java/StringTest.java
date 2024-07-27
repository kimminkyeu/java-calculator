import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("String 테스트")
public class StringTest {

	@Test
	@DisplayName("(1) \"1,2\"을 , 로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.")
	public void givenString_whenSplit_thenReturnCorrentResult() {

		assertThat("1,2".split(",")).contains("1", "2");

		assertThat("1".split(",")).containsExactly("1");
	}

	@Test
	@DisplayName("(2) \"(1,2)\" 값이 주어졌을 때 String의 substring() 메소드를 활용해 () 을 제거하고 \"1,2\"를 반환하도록 구현한다.")
	public void givenString_whenSubstring_thenReturnCorrentResult() {

		assertThat("(1, 2)".substring(1, 5)).isEqualTo("1, 2");
	}

	@Test
	@DisplayName("(3) \"abc\" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.")
	public void givenString_whenIndexOutOfBoundsException_thenReturnCorrentResult() {

		assertThat("abc".charAt(1)).isEqualTo('b');

		assertThatThrownBy(() -> {
			"abc".charAt(100);
		}).isInstanceOf(StringIndexOutOfBoundsException.class);

		/*
		assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> {
			"abc".charAt(100);
		}).withMessageMatching("Index: \\d+, Size: \\d+");
		 */
	}
}