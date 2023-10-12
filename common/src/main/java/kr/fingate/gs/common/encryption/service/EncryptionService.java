package kr.fingate.gs.common.encryption.service;

import kr.fingate.gs.common.encryption.dao.EncryptionDao;
import kr.fingate.gs.common.vo.AesKeyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EncryptionService {

    final EncryptionDao encryptionDao;

    public AesKeyVO getAeskey() throws Exception {
        AesKeyVO aesKeyVO = new AesKeyVO();

        try {
            aesKeyVO = encryptionDao.getAeskeyM();
        } catch (Exception ssee) {
            aesKeyVO = encryptionDao.getAeskeyO();
        }

        if(aesKeyVO.getAkey().length() > 32) {
            byte[] bytes = Hex.decodeHex(aesKeyVO.getAkey().toCharArray());
            aesKeyVO.setAkey(new String(bytes, "UTF-8"));
            // 3230413342303041313248333937453132323843323233334A33334433313143
        }

        return aesKeyVO;
    }
}
