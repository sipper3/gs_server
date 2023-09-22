package kr.fingate.gs.comon.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseVO {

	// 클라이언트번호
	@JsonIgnore
	private long clientNo;

	// 등록일
	private String regDate;

	// 등록자NO
	private long regUserNo;

	// 등록자명
	private String regUserName;

	// 수정일
	private String modDate;

	// 수정자NO
	private long modUserNo;

	// 수정자명
	private String modUserName;


}
