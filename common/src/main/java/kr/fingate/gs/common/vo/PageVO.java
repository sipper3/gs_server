package kr.fingate.gs.common.vo;

import kr.fingate.gs.common.annotation.Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageVO extends BaseVO {

	@Info(value = "페이지번호", description = "현재 페이지 번호")
	private int pageNum;

	@Info(value = "페이지사이즈", description = "한페이지당 데이터 수")
    private int pageSize;

}
