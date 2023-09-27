package kr.fingate.gs.auth.login.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.auth.AuthApplication;
import kr.fingate.gs.auth.role.dto.RoleInsDto;
import kr.fingate.gs.auth.role.dto.SearchRoleDto;
import kr.fingate.gs.auth.vo.RoleItemMapVO;
import kr.fingate.gs.common.Item;
import kr.fingate.gs.common.TestBaseController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.Schema.schema;
import static kr.fingate.gs.common.Item.field;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.*;

@Transactional(rollbackFor=Exception.class)
@SpringBootTest(classes = {AuthApplication.class})
public class LoginPublicControllerTest extends TestBaseController {

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("토큰발급")
    void testGetToken() throws Exception {

        //given

        Item responseItems = field(
            field("token", STRING, "토큰", Item.State.OPTIONAL)
        );
//        List<FieldDescriptor> requestFields = generateFieldDescriptor(searchRoleDto, "searchRoleName", "searchRoleNo");

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/auth/api/sso/getToken")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(searchRoleDto))
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("롤조회") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("개별롤에 대한 롤 조회") // 문서에 표시될 상세정보
                        .requestSchema(schema("SearchRoleDto"))
//                        .requestFields(requestFields)
                        .responseFields(responseItems.toFields())
                        .build(), true);
    }

}
