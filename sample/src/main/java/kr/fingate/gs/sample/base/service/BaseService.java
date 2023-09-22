package kr.fingate.gs.sample.base.service;

import kr.fingate.gs.sample.base.dao.BaseDao;
import kr.fingate.gs.sample.base.dto.SampleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseService{

    private final BaseDao baseDao;

    public SampleDto getSelectOne()  throws Exception {
        return baseDao.getSelectOne();
    }
}
