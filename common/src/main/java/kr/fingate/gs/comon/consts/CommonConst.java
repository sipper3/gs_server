package kr.fingate.gs.comon.consts;

import java.io.File;

public class CommonConst {


    // Fingate Platform
    static public final long FINPL_CLIENT_NO = 100000L;

    static public final String DATA_STATE_DELETE = "D";
    static public final String DATA_STATE_ACTIVE = "A";

    static public final String YES = "Y";
    static public final String NO = "N";

    // FileUtil Const
    static public final int FILE_NOT_ALLOWED = -9;                 // 허용 되지 않는 파일 타입의 업로드
    static public final int FILE_SUCCESS_UPLOAD = 1;             // 정상 업로드
    static public final String FILE_SEPARATOR = File.separator;
}
