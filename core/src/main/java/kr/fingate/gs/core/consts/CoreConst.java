package kr.fingate.gs.core.consts;

import java.io.File;

public class CoreConst {
    // FileUtil Const
    static public final int FILE_NOT_ALLOWED = -9;                  // 허용 되지 않는 파일 타입의 업로드
    static public final int FILE_SUCCESS_UPLOAD = 1;                // 정상 업로드
    static public final int FILE_FAIL_UPLOAD = -1;                  // 업로드 실패
    static public final String FILE_SEPARATOR = File.separator;
}
