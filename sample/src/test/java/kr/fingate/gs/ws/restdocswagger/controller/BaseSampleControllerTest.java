package kr.fingate.gs.ws.restdocswagger.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.common.Item;
import kr.fingate.gs.common.TestBaseController;
import kr.fingate.gs.common.security.WithTestUser;
import kr.fingate.gs.core.consts.enums.TokenType;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.sample.SampleApplication;
import kr.fingate.gs.sample.base.dto.SampleDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.Schema.schema;
import static kr.fingate.gs.common.Item.field;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;

@Transactional(rollbackFor=Exception.class)
@SpringBootTest(classes = {SampleApplication.class})
public class BaseSampleControllerTest extends TestBaseController {

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("상담조회")
    void getCnsl() throws Exception {

        //given
        SampleDto sampleDto = SampleDto.builder()
                .cnslNo(5)
                .custNo(1)
                .build();

        Item items = field(
                        field("custNo", NUMBER, "고객번호"),
                        field("cnslNo", NUMBER, "상담번호")
                );

        //when
        ResultActions resultActions = mockMvc.perform(post("/sample/api/base/test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                .content(objectMapper.writeValueAsString(sampleDto))
                );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("상담") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("상담번호, 고객번호를 조회한다") // 문서에 표시될 상세정보
                        .requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer + 인증정보")
                        )
                        .requestSchema(schema("CnslRqstDto")) // 문서에 표시될 요청객체 정보
                        .requestFields(items.toFields())
                        .responseFields(items.toFields())
                        .build());
    }

    @Test
    @DisplayName("No return")
    void getNoReturn() throws Exception {

        //given
        SampleDto sampleDto = SampleDto.builder()
                .cnslNo(5)
                .custNo(1)
                .build();

        Item items = field(
                field("custNo", NUMBER, "고객번호"),
                field("cnslNo", NUMBER, "상담번호")
        );

        //when
        ResultActions resultActions = mockMvc.perform(post("/sample/api/base/noreturn")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                .content(objectMapper.writeValueAsString(sampleDto))
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("상담") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("상담번호, 고객번호를 조회한다") // 문서에 표시될 상세정보
                        .requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer + 인증정보")
                        )
                        .requestSchema(schema("CnslRqstDto")) // 문서에 표시될 요청객체 정보
                        .requestFields(items.toFields())
                        .build());
    }

    @Test
    @WithTestUser // Test용 Token
    @DisplayName("Token O, request O, response X")
    void getTokenNoreturn() throws Exception {

        //given
        Map<String, Object> map = new HashMap<>();
        map.put("keyStr", "value1");
        map.put("keyStr2", "value2");
        map.put("keyInt", 123);

        Item items = field(
                field("keyStr", STRING, "data1"),
                field("keyStr2", STRING, "data2", Item.State.OPTIONAL), // 필수값 X
                field("keyInt", NUMBER, "data3", Item.State.OPTIONAL) // 필수값 X
        );

        //when
        ResultActions resultActions = mockMvc.perform(post("/sample/api/base/token/noreturn")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(TokenType.ACCESS_TOKEN.getHeader(), "login으로 발급 받은 " + TokenType.ACCESS_TOKEN.getSubject())
                .content(objectMapper.writeValueAsString(map))
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("TEST code 작성") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("Token인증이 필요하며 RequestBody가 있고 ResponseBody는 없는 경우") // 문서에 표시될 상세정보
                        .requestHeaders(
                                headerWithName(TokenType.ACCESS_TOKEN.getHeader()).description("login으로 발급 받은 " + TokenType.ACCESS_TOKEN.getSubject())
                        )
                        .requestSchema(schema("Map"))
                        .requestFields(items.toFields())
                        .build());
    }

    @Test
    @WithTestUser // Test용 Token
    @DisplayName("Token O, request X, response O")
    void getTokenNorequest() throws Exception {

        Item responseItems = field(
                field("clientNo", NUMBER, "클라이언트번호"),
                field("userNo", NUMBER, "사용자번호"),
                field("userName", STRING, "사용자이름")
        );

        //when
        ResultActions resultActions = mockMvc.perform(post("/sample/api/base/token/norequest")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(TokenType.ACCESS_TOKEN.getHeader(), "login으로 발급 받은 " + TokenType.ACCESS_TOKEN.getSubject())
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("TEST code 작성") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("Token인증이 필요하며 RequestBody가 없고 ResponseBody는 있는 경우") // 문서에 표시될 상세정보
                        .requestHeaders(
                                headerWithName(TokenType.ACCESS_TOKEN.getHeader()).description("login으로 발급 받은 " + TokenType.ACCESS_TOKEN.getSubject())
                        )
                        .responseFields(responseItems.toFields())
                        .build());
    }

    @Test
    @DisplayName("Token X, pathVariable O, request O, response O")
    void getPathPathData() throws Exception {

        UserTokenDto userTokenDto = UserTokenDto.builder()
                .clientNo(100000L)
                .userNo(0)
                .userName("Tester")
                .build();

        Item requestItems = field(
                field("clientNo", NUMBER, "클라이언트번호"),
                field("userNo", NUMBER, "사용자번호"),
                field("userName", STRING, "사용자이름")
        );

        Item responseItems = field(
                field("clientNo", NUMBER, "변환클라이언트번호"),
                field("userNo", NUMBER, "변환사용자번호"),
                field("userName", STRING, "변환사용자이름"),
                field("pathdata", STRING, "경로변수")
        );

        //when
        ResultActions resultActions = mockMvc.perform(post("/sample/api/base/notoken/path/{pathdata}", "transtester123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userTokenDto))
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("TEST code 작성") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("Token인증이 필요없고(yml설정필요), pathVariable 및 RequestBody가 있고 ResponseBody도 있는 경우") // 문서에 표시될 상세정보
                        .requestSchema(schema("UserTokenDto"))
                        .requestFields(requestItems.toFields())
                        .responseFields(responseItems.toFields())
                        .build());
    }


}
