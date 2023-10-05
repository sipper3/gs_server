package kr.fingate.gs.common.dto.hstry;

import kr.fingate.gs.common.vo.HstryVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("HstryDto")
public class HstryDto extends HstryVO implements Cloneable {

    @Override
    public HstryDto clone() throws CloneNotSupportedException {
        return (HstryDto) super.clone();
    }
}
