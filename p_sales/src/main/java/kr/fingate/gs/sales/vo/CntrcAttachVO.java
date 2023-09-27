package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcAttachVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="파일순번")
    private long fileSeq;

    @Info(value="파일구분")
    private String fileClass;

    @Info(value="파일형식")
    private String fileFormat;

    @Info(value="파일크기(Byte)")
    private int fileSize;

    @Info(value="파일경로")
    private String filePath;

    @Info(value="파일명")
    private String fileName;

    @Info(value="원본파일명")
    private String orgnlFileName;

    @Info(value="데이터상태")
    private String dataStatus;

}
