package kr.fingate.gs.auth.hstry.service;

import kr.fingate.gs.auth.hstry.dao.HstryDao;
import kr.fingate.gs.auth.hstry.dao.HstryModDao;
import kr.fingate.gs.common.dto.hstry.HstryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HstryService {

    final HstryDao hstryDao;
    final HstryModDao hstryModDao;


    public void insRoleHstry(List<HstryDto> hstryList) throws Exception {

        if (hstryList.isEmpty()) return;
        hstryModDao.insRoleHstry(hstryList);

    }

    public List<HstryDto> getRoleHstryList(HstryDto hstryDto) throws Exception {
        return hstryDao.getRoleHstryList(hstryDto);
    }

}
