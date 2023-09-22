package kr.fingate.gs.comon.dto.hstry;

import kr.fingate.gs.comon.vo.HstryVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HstryDto extends HstryVO implements Cloneable {

    @Override
    public HstryDto clone() throws CloneNotSupportedException {
        return (HstryDto) super.clone();
    }
}
