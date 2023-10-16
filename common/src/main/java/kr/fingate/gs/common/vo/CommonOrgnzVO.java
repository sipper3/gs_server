package kr.fingate.gs.common.vo;

import kr.fingate.gs.common.annotation.Info;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@Alias("CommonOrgnzVO")
public class CommonOrgnzVO {

    @Info(value="조직번호")
    private long orgnzNo;

    @Info(value="조직명")
    private String orgnzName;

    @Info(value="부모조직번호")
    private long parentDprtmNo;

    @Info(value="사용여부")
    private String useYn;

}
