package kr.fingate.gs.common.h2.dao;

import kr.fingate.gs.common.vo.AesKeyVO;
import org.springframework.stereotype.Repository;

@Repository("H2")
public interface H2Dao {

    void createH2Aeskey();
    AesKeyVO getH2Aeskey();
    void insH2Aeskey(AesKeyVO params);
}
