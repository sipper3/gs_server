package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("LunarDateVO")
public class LunarDateVO extends BaseVO {

    @Info(value="양력일")
    private String solarDate;

    @Info(value="요일")
    private String dayWeek;

    @Info(value="음력일")
    private String lunarDate;

    @Info(value="윤달여부")
    private String lunarLeapYn;

    @Info(value="음력해명")
    private String lunarYear;

    @Info(value="음력월명")
    private String lunarMonth;

    @Info(value="음력일명")
    private String lunarDay;

    @Info(value="휴일여부")
    private String hldyYn;

    @Info(value="휴일명")
    private String hldyName;

}
