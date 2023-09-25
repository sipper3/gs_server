package kr.fingate.gs.comon.dto.file;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class FileDto {
    private String fileName;
    private String fileFieldName;
    private long fileSize;
    private String filePath;
    private String originFileName;
    // 파일 확장자
    private String fileExt;

    // 처리상태 1: 정상, -8:최대 허용 사이즈 초과, -9:허용되지 않는 파일형식
    private int status = 0;
}
