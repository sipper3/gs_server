package kr.fingate.gs.rpa.job.dto;

import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchJobDto extends PageVO {
    private long scenId;
    private String regId;
}
