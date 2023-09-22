package kr.fingate.gs.auth.role.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.auth.AuthApplication;
import kr.fingate.gs.auth.role.dto.SearchRoleDto;
import kr.fingate.gs.common.Item;
import kr.fingate.gs.common.TestBaseController;
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
import static org.springframework.restdocs.payload.JsonFieldType.*;

@Transactional(rollbackFor=Exception.class)
@SpringBootTest(classes = {AuthApplication.class})
public class RoleControllerTest extends TestBaseController {

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("롤 조회")
    void testRoleList() throws Exception {

        //given
        SearchRoleDto searchRoleDto = new SearchRoleDto();
        searchRoleDto.setPageNum(1);
        searchRoleDto.setPageSize(20);

        Item requestItems = field(
                field("searchRoleName", STRING, "searchkey에 대한 입력값", Item.State.IGNORED),
                field("searchRoleNo", STRING, "searchkey에 대한 입력값", Item.State.IGNORED),
                field("searchKey", STRING, "roleName : 권한명, roleDscrp : 권한설명", Item.State.IGNORED),
                field("searchVal", STRING, "searchkey에 대한 입력값", Item.State.IGNORED),
                field("searchUseYn", STRING, "사용여부 : Y, N", Item.State.IGNORED)
        );

        Item responseItems = field(
                        field("roleNo", NUMBER, "권한 Unique", Item.State.IGNORED),
                        field("roleName", NUMBER, "권한명", Item.State.IGNORED),
                        field("roleDscrp", STRING, "권한 상세 설명", Item.State.IGNORED),
                        field("useYn", STRING, "사용여부", Item.State.IGNORED),
                        field("regDate", STRING, "사용여부", Item.State.IGNORED),
                        field("regUserNo", STRING, "사용여부", Item.State.IGNORED),
                        field("regUserName", STRING, "사용여부", Item.State.IGNORED),
                        field("modDate", STRING, "사용여부", Item.State.IGNORED),
                        field("modUserNo", STRING, "사용여부", Item.State.IGNORED),
                        field("modUserName", STRING, "사용여부", Item.State.IGNORED)
//                )
        );

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/auth/api/role/getRoleList")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(searchRoleDto))
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("롤조회") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("개별롤에 대한 롤 조회") // 문서에 표시될 상세정보
                        .requestSchema(schema("SearchRoleDto"))
                        .requestFields(requestItems.toFields())
                        .responseFields(responseItems.toFields())
                        .build(), true, true);
    }
}
