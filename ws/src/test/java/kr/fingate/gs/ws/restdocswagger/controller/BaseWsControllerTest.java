package kr.fingate.gs.ws.restdocswagger.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.common.Item;
import kr.fingate.gs.common.TestBaseController;
import kr.fingate.gs.ws.WsApplication;
import kr.fingate.gs.ws.base.dto.WsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.Schema.schema;
import static kr.fingate.gs.common.Item.field;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;


@SpringBootTest(classes = {WsApplication.class})
public class BaseWsControllerTest extends TestBaseController {

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("상담조회")
    void getCnsl() throws Exception {

        //given
        WsDto wsDto = WsDto.builder()
                .cnslNo(5)
                .custNo(1)
                .build();

        Item items = field(
                        field("custNo", NUMBER, "고객번호"),
                        field("cnslNo", NUMBER, "상담번호")
                );

        //when
        ResultActions resultActions = mockMvc.perform(post("/ws/api/base/test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                .content(objectMapper.writeValueAsString(wsDto))
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

}
