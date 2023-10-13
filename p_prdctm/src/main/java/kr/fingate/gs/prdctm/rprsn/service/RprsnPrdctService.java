package kr.fingate.gs.prdctm.rprsn.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.core.service.CoreService;
import kr.fingate.gs.prdctm.rprsn.dao.RprsnPrdctDao;
import kr.fingate.gs.prdctm.rprsn.dao.RprsnPrdctModDao;
import kr.fingate.gs.prdctm.rprsn.dto.RprsnPrdctDto;
import kr.fingate.gs.prdctm.rprsn.dto.SearchRprsnPrdctDto;
import kr.fingate.gs.prdctm.vo.RprsnPrdctVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class RprsnPrdctService {

    final RprsnPrdctDao rprsnPprdctDao;
    final RprsnPrdctModDao rprsnPrdctModDao;

    public Page<RprsnPrdctDto> getRprsnPrdctList(SearchRprsnPrdctDto searchPrdctDto) throws Exception {
        PageHelper.startPage(searchPrdctDto.getPageNum(), searchPrdctDto.getPageSize());
        return rprsnPprdctDao.getRprsnPrdctList(searchPrdctDto);
    }

    public RprsnPrdctDto getRprsnPrdct(SearchRprsnPrdctDto searchPrdctDto) throws Exception {
        return rprsnPprdctDao.getRprsnPrdct(searchPrdctDto);
    }

    public Long insRprsnPrdct(RprsnPrdctVO rprsnPrdctVO) throws Exception {
        rprsnPrdctVO.setRegUserNo(CoreService.getUserInfo().getUserNo());
        rprsnPrdctModDao.insRprsnPrdct(rprsnPrdctVO);

        return rprsnPrdctVO.getRprsnPrdctCode();
    }

    public void updRprsnPrdct(@RequestBody RprsnPrdctVO rprsnPrdctVO) throws Exception {
        rprsnPrdctVO.setModUserNo(CoreService.getUserInfo().getUserNo());
        rprsnPrdctModDao.updRprsnPrdct(rprsnPrdctVO);
    }

    public void delRprsnPrdct(@RequestBody RprsnPrdctVO rprsnPrdctVO) throws Exception {
        rprsnPrdctVO.setModUserNo(CoreService.getUserInfo().getUserNo());
        rprsnPrdctModDao.delRprsnPrdct(rprsnPrdctVO);
    }
}
