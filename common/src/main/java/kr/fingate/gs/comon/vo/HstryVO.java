package kr.fingate.gs.comon.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("HstryVO")
public class HstryVO extends BaseVO {

    // 이력순번
    private  long hstrySeq;

    // 엔티티명 (엔티티(실체)명:테이블명)
    private  String enttyName;

    // 속성명 (속성명:컬럼명)
    private  String atrbtName;

    // 엔티티KEY1
    private  Object enttyKey1;

    // 엔티티KEY2
    private  Object enttyKey2;

    // 본래값
    private  Object orgnlData;

    // 변경값
    private  Object changeData;

    // 운영유형
    private  String oprtnType;

    // 사유번호
    private  long reasonNo;

}
