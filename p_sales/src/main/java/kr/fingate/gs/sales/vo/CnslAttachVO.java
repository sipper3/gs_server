package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CnslAttachVO")
public class CnslAttachVO extends BaseVO {

    @Info(value="첨부파일순번")
    private long attachFileSeq;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="원본파일명")
    private String orgnlFileName;

    @Info(value="파일명")
    private String fileName;

    @Info(value="파일종류")
    private String fileKind;

    @Info(value="파일경로")
    private String filePath;

    @Info(value="파일크기")
    private int fileSize;

    @Info(value="파일형식")
    private String fileFormat;

    @Info(value="데이터상태")
    private String dataState;

    @Info(value="파일구분")
    private String fileClass;

}
