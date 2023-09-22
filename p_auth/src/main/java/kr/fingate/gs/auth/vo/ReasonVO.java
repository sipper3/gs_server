package kr.fingate.gs.auth.vo;

import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReasonVO extends BaseVO {

    // 이력순번
    private  long hstrySeq;

    // 엔티티명
    private  String enttyName;

    // 엔티티KEY1
    private  Object enttyKey1;

    // 엔티티KEY2
    private  Object enttyKey2;

    // 속성명
    private  String atrbtName;

    // 본래값
    private  String orgnlData;

    // 변경값
    private  String changeData;

    // 운영유형
    private  String oprtnType;

    // 사유번호
    private  long reasonNo;

}
