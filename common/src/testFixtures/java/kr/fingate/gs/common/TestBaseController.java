package kr.fingate.gs.common;

import com.epages.restdocs.apispec.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.fingate.gs.comon.annotation.Info;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
//import org.springframework.web.filter.CharacterEncodingFilter;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith({RestDocumentationExtension.class})
public abstract class TestBaseController {


    @Autowired
    protected WebApplicationContext ctx;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    void setUp(final RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
//                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

    }

    // 요청 공통 응답 Spec 부분 추출
    FieldDescriptor[] responseFields = {fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
            PayloadDocumentation.subsectionWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터").optional(),
            PayloadDocumentation.subsectionWithPath("error").type(JsonFieldType.OBJECT).description("오류 메시지").optional()
    };

    //PageVO
    FieldDescriptor[] pageVOFields = {
            fieldWithPath("regDate").type(JsonFieldType.STRING).description("등록일").optional(),
            fieldWithPath("regUserNo").type(JsonFieldType.NUMBER).description("등록자").optional(),
            fieldWithPath("regUserName").type(JsonFieldType.STRING).description("등록자명").optional(),
            fieldWithPath("modDate").type(JsonFieldType.STRING).description("수정일").optional(),
            fieldWithPath("modUserNo").type(JsonFieldType.NUMBER).description("수정자").optional(),
            fieldWithPath("modUserName").type(JsonFieldType.STRING).description("수정자명").optional(),
            fieldWithPath("pageNum").type(JsonFieldType.NUMBER).description("현재페이지").optional(),
            fieldWithPath("pageSize").type(JsonFieldType.NUMBER).description("페이지당 수").optional()
    };

    //PageVO
    FieldDescriptor[] pageInfoFields = {
            fieldWithPath("total").type(JsonFieldType.NUMBER).description("총건수").optional(),
            fieldWithPath("pageNum").type(JsonFieldType.NUMBER).description("현재페이지").optional(),
            fieldWithPath("pageSize").type(JsonFieldType.NUMBER).description("페이지당 수").optional(),
            fieldWithPath("size").type(JsonFieldType.NUMBER).description("현재 페이지수").optional(),
            subsectionWithPath("list").type(ARRAY).description("데이터").optional(),
//            PayloadDocumentation.fieldWithPath("startRow").type(JsonFieldType.NUMBER).description("").optional(),
//            PayloadDocumentation.fieldWithPath("endRow").type(JsonFieldType.OBJECT).description("").optional(),
//            PayloadDocumentation.fieldWithPath("pages").type(JsonFieldType.NUMBER).description("").optional(),
    };

    public ResultActions generateDocument(ResultActions resultAction, ResourceSnippetParameters rp) throws Exception {
        return generateDocument(resultAction, rp, false, false);
    }

    public ResultActions generateDocument(ResultActions resultAction, ResourceSnippetParameters rp, boolean isPageResp) throws Exception {
        return generateDocument(resultAction, rp, isPageResp, false);
    }

    public ResultActions generateDocument(ResultActions resultAction, ResourceSnippetParameters rp, boolean isPageResp, boolean isPageRqst) throws Exception {

        // epages 의 ResourceSnippetParameters는 ascii에서 fields 파일을 생성x -> 기존 restdoc과 openapi용 epages를 동시에 사용해야하므로 해당 메소드 사용필요
//        List<FieldDescriptor> fields = rp.getResponseFields();
//        FieldDescriptors responseF = new FieldDescriptors(responseFields).and(fields.toArray(new FieldDescriptor[0]));

        // Request Body가 없는 경우 Request Body용 Snippet을 생성하지 않는다.
        boolean isRequestFields = !rp.getRequestFields().isEmpty();
        boolean isResponseFields = !rp.getResponseFields().isEmpty();
        FieldDescriptors rqstFields = new FieldDescriptors(rp.getRequestFields());
        FieldDescriptors respFields = new FieldDescriptors(rp.getResponseFields());

        //PageVO 상속받은 request 사용 시
        if(isRequestFields && isPageRqst) {
            rqstFields = rqstFields.and(pageVOFields);
        }

        //PageInfoDto return 사용 시
        if(isResponseFields && isPageResp) {

            List<FieldDescriptor> orignalFields = rp.getResponseFields();
            List<FieldDescriptor> newFields = new ArrayList<>();
            String prefix = "list[].";
            orignalFields.forEach(f -> {
                FieldDescriptor newField = fieldWithPath(prefix + f.getPath()).type(f.getType()).description(f.getDescription());
                if(f.isIgnored()) {
                    newField.ignored();
                }
                if(f.isOptional()) {
                    newField.optional();
                }
                newFields.add(newField);
            });
            respFields = new FieldDescriptors(newFields).and(pageInfoFields);
        }

        Snippet[] snippets = new Snippet[isRequestFields? 2 : 1];
        snippets[0] = PayloadDocumentation.responseFields (
                (!isResponseFields ? null : PayloadDocumentation.beneathPath("data").withSubsectionId("data")),
                (!isResponseFields ? Arrays.stream(responseFields).toList() : respFields.getFieldDescriptors())
        );

        if(isRequestFields){
            snippets[1] = PayloadDocumentation.requestFields (
                    rqstFields.getFieldDescriptors()
            );
        }

        ResourceSnippetParameters newSnippet = ResourceSnippetParameters.builder()
                .tags(rp.getTags().toArray(new String[0]))
                .description(rp.getDescription())
                .deprecated(rp.getDeprecated())
                .formParameters(rp.getFormParameters())
                .links(rp.getLinks())
                .privateResource(rp.getPrivateResource())
                .requestSchema(rp.getRequestSchema())
                .responseSchema(Schema.schema("ResponseDto"))
                .responseHeaders(rp.getResponseHeaders())
                .requestHeaders(rp.getRequestHeaders())
                .requestFields(rqstFields)
                .pathParameters(rp.getPathParameters())
                .queryParameters(rp.getQueryParameters())
                .responseFields(responseFields)
                .summary(rp.getSummary())
                .build();


        resultAction.andDo(MockMvcRestDocumentation.document(
                        "{class-name}/{method-name}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        snippets
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        "{class-name}/{method-name}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        ResourceDocumentation.resource(newSnippet)));
        return resultAction;
    }


    /**
     * Info Annotation 기반 List<FieldDescriptor> 생성
     * @param t 생성 대상 DTO
     * @param isNotDefault default 값 제외 여부
     * @param requiredItems 필수 항목
     * @param items restDoc에 표기할 항목
     * @return
     * @throws Exception
     */
    public <T> List<FieldDescriptor> generateFieldDescriptor(T t, boolean isNotDefault, String[] requiredItems, String... items) throws Exception {

        return getFieldDescriptors(t, "", isNotDefault, requiredItems, items);
    }

    /**
     * Info Annotation 기반 List<FieldDescriptor> 생성
     * @param t 생성 대상 DTO
     * @param requiredItems 필수 항목
     * @param items restDoc에 표기할 항목
     * @return
     * @throws Exception
     */
    public <T> List<FieldDescriptor> generateFieldDescriptor(T t, String[] requiredItems, String... items) throws Exception {

        return getFieldDescriptors(t, "", false, requiredItems, items);
    }

    public <T> List<FieldDescriptor> generateFieldDescriptor(T t, String... items) throws Exception {

        return getFieldDescriptors(t, "", false, new String[]{}, items);
    }

    private <T> List<FieldDescriptor> getFieldDescriptors(T c, String superPath, boolean isNotDefault, String[] requiredItems, String... items) throws Exception{

        List<FieldDescriptor> result = new ArrayList<>();
        Field[] fields = getFields(c.getClass());

        if(fields != null) {
            JsonInclude jsonInclude = c.getClass().getAnnotation(JsonInclude.class);
            if(jsonInclude != null && jsonInclude.value() == JsonInclude.Include.NON_DEFAULT) {
                isNotDefault = true;
            }

            for(Field field : fields) {

                JsonIgnore ignore = field.getAnnotation(JsonIgnore.class);
                if(ignore != null) {
                    continue;
                }

                boolean isChild = false;
                String path = superPath + field.getName();
                String name = "ERROR : @Info Annotation required!!!";
                String desc = "";

                field.setAccessible(true);
                Object value = field.get(c);

                // default 체크
                if(isNotDefault) {
                    if(value == null) continue;
                    if(field.getType().isAssignableFrom(int.class) && (int)value == 0) {
                        continue;
                    }else if(field.getType().isAssignableFrom(long.class) && (long)value == 0) {
                        continue;
                    }else if(field.getType().isAssignableFrom(float.class) && (float)value == 0.0f) {
                        continue;
                    }else if(field.getType().isAssignableFrom(double.class) && (double)value == 0.0d) {
                        continue;
                    }
                }

                // JSON type 지정
                JsonFieldType jsonFieldType;
                if(field.getType().isAssignableFrom(String.class)){
                    jsonFieldType = STRING;
                }else if(field.getType().isAssignableFrom(int.class)
                        || field.getType().isAssignableFrom(long.class)
                        || field.getType().isAssignableFrom(float.class)
                        || field.getType().isAssignableFrom(double.class)
                        || field.getType().isAssignableFrom(BigInteger.class)
                        || field.getType().isAssignableFrom(BigDecimal.class) ){
                    jsonFieldType = NUMBER;
                }else if(field.getType().isAssignableFrom(List.class) || field.getType().isAssignableFrom(Arrays.class) ){
                    jsonFieldType = ARRAY;
                    isChild = true;
                }else if(field.getType().isAssignableFrom(Boolean.class) ){
                    jsonFieldType = BOOLEAN;
                }else {
                    jsonFieldType = OBJECT;
                    isChild = true;
                }

                Info fieldInfo = field.getAnnotation(Info.class);
                if(fieldInfo != null) {
                    name = fieldInfo.value();

                    if(fieldInfo.description() != null && !fieldInfo.description().equals("")) {
                        desc = " : " + fieldInfo.description();
                    }
                }

                FieldDescriptor f = PayloadDocumentation.fieldWithPath(path)
                        .type(jsonFieldType)
                        .description(name + desc);

                if(isNotDefault || Arrays.asList(items).contains(path)) {
                    if(!Arrays.asList(requiredItems).contains(path)) {
                        f.optional();
                    }
                }else{
                    f.ignored();
                }

                result.add(f);

                if(isChild) {
                    if(jsonFieldType.equals(ARRAY)) {
                        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                        Class child = (Class<?>) genericType.getActualTypeArguments()[0];
                        ArrayList list = (ArrayList) value;
                        if(child.getPackageName().contains("fingate") && !list.isEmpty()) {
                            result.addAll(getFieldDescriptors(list.get(0), path+"[].", isNotDefault, requiredItems, items));
                        }
                    }else{
                        result.addAll(getFieldDescriptors(field, path+".", isNotDefault, requiredItems, items));
                    }
                }
            }
        }

        return result;
    }
    private Field[] getFields(Class c){

        Field[] fields = c.getDeclaredFields();

        Class superClazz = c.getSuperclass();
        if(superClazz != null){
            return ArrayUtils.addAll(fields, getFields(superClazz));
        }

        return fields;
    }

}