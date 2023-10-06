package kr.fingate.gs.common.encryption.service;

import kr.fingate.gs.common.encryption.dao.EncryptionDao;
import kr.fingate.gs.common.vo.AesKeyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptionService {

    final EncryptionDao encryptionDao;

    public AesKeyVO getAeskey() throws Exception {
        return encryptionDao.getAeskey();
    }
}
