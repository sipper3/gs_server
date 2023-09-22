package kr.fingate.gs.ws.base.service;

import kr.fingate.gs.ws.base.dao.BaseDao;
import kr.fingate.gs.ws.base.dto.WsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseService{

    private final BaseDao baseDao;

    public WsDto getSelectOne()  throws Exception {
        return baseDao.getSelectOne();
    }
}
