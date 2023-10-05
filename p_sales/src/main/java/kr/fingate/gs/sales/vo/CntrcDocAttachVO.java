package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcDocAttachVO")
public class CntrcDocAttachVO extends BaseVO {

    @Info(value="문서번호")
    private long docNo;

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

    @Info(value="상태")
    private String state;

}
