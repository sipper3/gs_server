package kr.fingate.gs.auth.role.controller;

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
                field("searchRoleName", STRING, "searchkey에 대한 입력값", Item.State.OPTIONAL),
                field("searchRoleNo", STRING, "searchkey에 대한 입력값", Item.State.OPTIONAL),
                field("searchKey", STRING, "roleName : 권한명, roleDscrp : 권한설명", Item.State.OPTIONAL),
                field("searchVal", STRING, "searchkey에 대한 입력값", Item.State.OPTIONAL),
                field("searchUseYn", STRING, "사용여부 : Y, N", Item.State.OPTIONAL)
        );

        Item responseItems = field(
                        field("roleNo", NUMBER, "권한 Unique", Item.State.OPTIONAL),
                        field("roleName", STRING, "권한명", Item.State.OPTIONAL),
                        field("roleDscrp", STRING, "권한 상세 설명", Item.State.OPTIONAL),
                        field("useYn", STRING, "사용여부", Item.State.OPTIONAL),
                        field("regDate", STRING, "사용여부", Item.State.OPTIONAL),
                        field("regUserNo", NUMBER, "사용여부", Item.State.OPTIONAL),
                        field("regUserName", STRING, "사용여부", Item.State.OPTIONAL),
                        field("modDate", STRING, "사용여부", Item.State.OPTIONAL),
                        field("modUserNo", NUMBER, "사용여부", Item.State.OPTIONAL),
                        field("modUserName", STRING, "사용여부", Item.State.OPTIONAL)
//                )
        );
        List<FieldDescriptor> requestFields = generateFieldDescriptor(searchRoleDto, "searchRoleName", "searchRoleNo");

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
                        .requestFields(requestFields)
                        .responseFields(responseItems.toFields())
                        .build(), true);
    }

    @Test
    @DisplayName("롤 저장")
    void testInsRole() throws Exception {

        //given
        RoleInsDto roleInsDto = new RoleInsDto();
        List<RoleItemMapVO> roleItemMapList = new ArrayList<RoleItemMapVO>();
        roleInsDto.setRoleName("Test Case Role");
        roleInsDto.setRoleDscrp("RoleDscrption of Test Case Role");
        roleInsDto.setUseYn("Y");
        RoleItemMapVO roleItemMapVO = new RoleItemMapVO();
        roleItemMapVO.setItemNo(0);
        roleItemMapVO.setDataState("A");
        roleItemMapList.add(roleItemMapVO);
        roleInsDto.setRoleItemMapVOList(roleItemMapList);

        Item requestItems = field(
                field("roleName", STRING, "역할명", Item.State.IGNORED),
                field("roleDscrp", STRING, "역할설명", Item.State.IGNORED),
                field("roleItemMapVOList", ARRAY, "항목(권한)", Item.State.IGNORED,
                    field("itemNo", STRING, "항목번호", Item.State.IGNORED)
                )
        );

        String[] requiredItems = new String[]{"roleNo", "roleName"};
        String[] items = new String[]{"roleNo", "roleName", "roleDscrp", "roleItemMapVOList", "roleItemMapVOList[].itemNo"};
        List<FieldDescriptor> requestFields = generateFieldDescriptor(roleInsDto, requiredItems, items);

        for(FieldDescriptor f : requestFields) {
            System.out.println( f.getPath() +  " : " + f.getType() + " / " + f.isIgnored() + " " + f.isOptional());
        }
        //when
        ResultActions resultActions = mockMvc.perform(
                post("/auth/api/role/getRoleList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roleInsDto))
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        generateDocument(resultActions,
                ResourceSnippetParameters.builder()
                        .tag("롤조회") // 문서에 표시될 태그.summary("사용자 정보 생성") // 문서에 표시될 요약정보
                        .description("개별롤에 대한 롤 조회") // 문서에 표시될 상세정보
                        .requestSchema(schema("SearchRoleDto"))
                        .requestFields(requestFields)
                        .build(), true);
    }
}
