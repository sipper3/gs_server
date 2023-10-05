package kr.fingate.gs.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.common.annotation.Info;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseVO {

	@JsonIgnore
	@Info("클라이언트번호")
	private long clientNo;

	@Info("등록일")
	private String regDate;

	@Info("등록자No")
	private long regUserNo;

	@Info("등록자명")
	private String regUserName;

	@Info("수정일")
	private String modDate;

	@Info("수정자No")
	private long modUserNo;

	@Info("수정자명")
	private String modUserName;


}
