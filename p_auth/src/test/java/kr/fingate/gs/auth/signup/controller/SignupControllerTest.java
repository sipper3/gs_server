package kr.fingate.gs.auth.signup.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.auth.AuthApplication;
import kr.fingate.gs.common.Item;
import kr.fingate.gs.common.TestBaseController;
import kr.fingate.gs.auth.signup.dto.SignupDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static com.epages.restdocs.apispec.Schema.schema;
import static kr.fingate.gs.common.Item.field;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;

@Transactional(rollbackFor=Exception.class)
@SpringBootTest(classes = {AuthApplication.class})
public class SignupControllerTest extends TestBaseController {

    @Autowired
    protected ObjectMapper objectMapper;

//    @Test
//    @DisplayName("회원가입")
//    void testInsUser() throws Exception {
//
//        //given
//        SignupDto signupDto = SignupDto.builder()
//                .clientNo(100000L)
//                .loginEmail("test@fingate.kr")
//                .loginPswrd("1234")
//                .build();
//
//        Item items = field(
//                field("clientNo", NUMBER, "클라이언트번호"),
//                field("loginEmail", STRING, "로그인 이메일"),
//                field("loginPswrd", STRING, "로그인 비밀번호"),
//                field("userNo", NUMBER, "사용자 번호", Item.State.OPTIONAL),
//                field("userName", STRING, "사용자 이름", Item.State.OPTIONAL),
//                field("userNiknm", STRING, "사용자 별명", Item.State.OPTIONAL),
//                field("cellTellNo", STRING, "휴대폰 번호", Item.State.OPTIONAL),
//                field("birthDate", STRING, "생일", Item.State.OPTIONAL),
//                field("genderType", STRING, "성별", Item.State.OPTIONAL),
//                field("authmEmailYn", STRING, "이메일 인증 여부", Item.State.OPTIONAL),
//                field("authmTellnoYn", STRING, "휴대폰 인증 여부", Item.State.OPTIONAL),
//                field("socialType", STRING, "소셜 구분", Item.State.OPTIONAL),
//                field("socialId", STRING, "소셜 ID", Item.State.OPTIONAL),
//                field("authmRole", STRING, "권한 구분", Item.State.OPTIONAL)
//        );
//
//        //when
//        ResultActions resultActions = mockMvc.perform(post("/auth/api/signup/ins")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(signupDto))
//        );
//        resultActions.andExpect(MockMvcResultMatchers.status().isInternalServerError());
//
//        generateDocument(resultActions,
//                ResourceSnippetParameters.builder()
//                        .tag("회원가입") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
//                        .description("unique 확인 후 회원가입 수행") // 문서에 표시될 상세정보
//                        .requestSchema(schema("SignupDto")) // 문서에 표시될 요청객체 정보
//                        .requestFields(items.toFields())
//                        .build());
//    }
}
