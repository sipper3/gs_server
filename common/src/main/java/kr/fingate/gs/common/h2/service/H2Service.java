package kr.fingate.gs.common.h2.service;

import kr.fingate.gs.common.encryption.service.EncryptionService;
import kr.fingate.gs.common.h2.dao.H2Dao;
import kr.fingate.gs.common.vo.AesKeyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class H2Service {

    final H2Dao h2Dao;
    final EncryptionService encryptionService;

    public void createH2Aeskey() throws Exception {
        h2Dao.createH2Aeskey();
    }

    public AesKeyVO getH2AesKey() throws Exception {
        AesKeyVO aesKeyVO = h2Dao.getH2Aeskey();
        if(ObjectUtils.isEmpty(aesKeyVO)) {
            aesKeyVO = encryptionService.getAeskey();
            h2Dao.insH2Aeskey(aesKeyVO);
        }

        return h2Dao.getH2Aeskey();
    }
}
