package practice.Practice2.user.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import practice.Practice.domain.auth.service.SignupService;
import practice.Practice.domain.user.presentation.UserController;

import static org.assertj.core.api.BDDAssumptions.given;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	SignupService signupService;

	@Test
	@DisplayName("유저 회원가입 테스트")
	void signUpTest() throws Exception {

		given(signupService.signUp("강태양", "강태양", "강태양",);)
	}

}
