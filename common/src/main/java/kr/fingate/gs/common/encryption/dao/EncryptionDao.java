package kr.fingate.gs.common.encryption.dao;

import kr.fingate.gs.common.vo.AesKeyVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EncryptionDao {
    AesKeyVO getAeskey();
}
