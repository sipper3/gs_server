package kr.fingate.gs.prdctm.prdct.service;

import com.github.pagehelper.Page;
import kr.fingate.gs.core.service.CoreService;
import kr.fingate.gs.prdctm.prdct.dao.PrdctDao;
import kr.fingate.gs.prdctm.prdct.dto.PrdctInfoDto;
import kr.fingate.gs.prdctm.prdct.dto.RprsnPrdctDto;
import kr.fingate.gs.prdctm.prdct.dto.SearchPrdctDto;
import kr.fingate.gs.prdctm.vo.RprsnPrdctVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class PrdctService {

    final PrdctDao prdctDao;

    public Page<PrdctInfoDto> getPrdctInfoList(SearchPrdctDto searchPrdctDto) throws Exception {
        return prdctDao.getPrdctInfoList(searchPrdctDto);
    }

    public Page<RprsnPrdctDto> getRprsnPrdctList(SearchPrdctDto searchPrdctDto) throws Exception {
        return prdctDao.getRprsnPrdctList(searchPrdctDto);
    }

    public Long insRprsnPrdt(RprsnPrdctVO rprsnPrdctVO) throws Exception {

        rprsnPrdctVO.setRegUserNo(CoreService.getUserInfo().getUserNo());
        prdctDao.insRprsnPrdt(rprsnPrdctVO);

        return rprsnPrdctVO.getRprsnPrdctCode();
    }

    public void updRprsnPrdt(@RequestBody RprsnPrdctVO rprsnPrdctVO) throws Exception {
        rprsnPrdctVO.setModUserNo(CoreService.getUserInfo().getUserNo());
        prdctDao.updRprsnPrdt(rprsnPrdctVO);
    }

}
