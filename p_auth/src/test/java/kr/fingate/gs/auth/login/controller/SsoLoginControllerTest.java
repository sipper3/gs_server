package kr.fingate.gs.auth.login.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.auth.AuthApplication;
import kr.fingate.gs.common.Item;
import kr.fingate.gs.common.TestBaseController;
import kr.fingate.gs.common.security.WithTestUser;
import kr.fingate.gs.core.consts.enums.TokenType;
import kr.fingate.gs.auth.login.dto.SsoLoginDto;
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
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;

@Transactional(rollbackFor=Exception.class)
@SpringBootTest(classes = {AuthApplication.class})
class SsoLoginControllerTest extends TestBaseController {

    @Autowired
    protected ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("로그인")
//    void testGetLogin() throws Exception {
//
//        //given
//        SsoLoginDto ssoLoginDto = SsoLoginDto.builder()
//                .clientNo(100000L)
//                .loginEmail("housney@fingate.kr")
//                .loginPswrd("qlalfqjsgh")
//                .build();
//
//        Item items = field(
//                field("loginEmail", STRING, "로그인 이메일"),
//                field("loginPswrd", STRING, "로그인 비밀번호"),
//                field("clientNo", NUMBER, "클라이언트번호", Item.State.OPTIONAL)
//        );
//
//        Item responseItem = field(
//                field("accessToken", STRING, "인증 Token")
//        );
//
//        //when
//        ResultActions resultActions = mockMvc.perform(post("/authm/api/sso/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(ssoLoginDto))
//        );
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
//
//        generateDocument(resultActions,
//                ResourceSnippetParameters.builder()
//                        .tag("통합 로그인") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
//                        .description("클라이언트번호, 이메일, 비밀번호를 받아 인증 Token을 발급") // 문서에 표시될 상세정보
//                        .requestSchema(schema("ssoLoginDto")) // 문서에 표시될 요청객체 정보
//                        .requestFields(items.toFields())
//                        .responseFields(responseItem.toFields())
//                        .build());
//    }
//
//    @Test
//    @WithTestUser
//    @DisplayName("Token 검사용 Token 정보 조회")
//    void testGetUser() throws Exception {
//        Item ResponseItems = field(
//                field("clientNo", NUMBER, "클라이언트번호"),
//                field("userNo", NUMBER, "사용자번호"),
//                field("userName", STRING, "사용자이름")
//        );
//
//        //when
//        ResultActions resultActions = mockMvc.perform(post("/auth/api/sso/get/user")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .header(TokenType.ACCESS_TOKEN.getHeader(), "login으로 발급 받은 " + TokenType.ACCESS_TOKEN.getSubject())
//        );
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
//
//        generateDocument(resultActions,
//                ResourceSnippetParameters.builder()
//                        .tag("통합 로그인") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
//                        .description("Token에 담긴 Data를 디코딩하여 반환") // 문서에 표시될 상세정보
//                        .requestHeaders(
//                                headerWithName(TokenType.ACCESS_TOKEN.getHeader()).description("login으로 발급 받은 " + TokenType.ACCESS_TOKEN.getSubject())
//                        )
//                        .responseFields(ResponseItems.toFields())
//                        .build());
//    }
}