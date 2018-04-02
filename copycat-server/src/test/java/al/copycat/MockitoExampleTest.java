package al.copycat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoExampleTest {

	interface Hello {
		String getMessage();
	}

	@BeforeEach
	public void initialize(@Mock Hello hello) {
		when(hello.getMessage()).thenReturn("world");
	}

	@Test
	public void test(@Mock Hello hello) {
		Assertions.assertThat(hello.getMessage()).isEqualTo("world");
	}

}
