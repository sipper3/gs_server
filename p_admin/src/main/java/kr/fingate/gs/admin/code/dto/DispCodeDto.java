package kr.fingate.gs.admin.code.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.fingate.gs.comon.vo.code.CodeVO;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // Null 값인 필드 제외
public class DispCodeDto extends CodeVO {

    @JsonIgnore
    private long regUserNo;

    @JsonIgnore
    private long modUserNo;

    @JsonIgnore
    private  int printOrder;
}
