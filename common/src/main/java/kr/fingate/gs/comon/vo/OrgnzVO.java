package kr.fingate.gs.comon.vo;

import kr.fingate.gs.comon.annotation.Info;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@Alias("OrgnzVO")
public class OrgnzVO {

    @Info(value="조직번호")
    private long orgnzNo;

    @Info(value="조직명")
    private String orgnzName;

    @Info(value="부모조직번호")
    private long parentDprtmNo;

    @Info(value="사용여부")
    private String useYn;

}
