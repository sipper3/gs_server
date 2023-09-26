package kr.fingate.gs.rpa.job.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.common.Item;
import kr.fingate.gs.common.TestBaseController;
import kr.fingate.gs.rpa.RpaApplication;
import kr.fingate.gs.rpa.job.dto.JobDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.Schema.schema;
import static kr.fingate.gs.common.Item.field;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;


@SpringBootTest(classes = {RpaApplication.class})
public class JobControllerTest extends TestBaseController {
    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("Next RPA Job")
    void testGetNextJob() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/rpa/api/job/getnextjob")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString())
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        Item responseItem = field(
                field("jobId", STRING, "Job Id")
        );

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("Next RPA Job") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("수행해야 할 RPA Job return") // 문서에 표시될 상세정보
//                        .requestSchema("nothing") // 문서에 표시될 요청객체 정보
//                        .requestFields(items.toFields())
                        .responseFields(responseItem.toFields())
                        .build());
    }
}
