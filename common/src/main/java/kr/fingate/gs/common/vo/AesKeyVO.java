package kr.fingate.gs.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("AesKeyVO")
public class AesKeyVO {

	@JsonIgnore
	private String type;

	@JsonIgnore
	private String akey;

	@JsonIgnore
	private String iv;

}
