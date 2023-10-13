package kr.fingate.gs.prdctm.prdct.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.prdctm.prdct.dao.PrdctDao;
import kr.fingate.gs.prdctm.prdct.dao.PrdctModDao;
import kr.fingate.gs.prdctm.prdct.dto.PrdctInfoDto;
import kr.fingate.gs.prdctm.prdct.dto.SearchPrdctDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrdctService {

    final PrdctDao prdctDao;
    final PrdctModDao prdctModDao;

    public Page<PrdctInfoDto> getPrdctInfoList(SearchPrdctDto searchPrdctDto) throws Exception {
        PageHelper.startPage(searchPrdctDto.getPageNum(), searchPrdctDto.getPageSize());
        return prdctDao.getPrdctInfoList(searchPrdctDto);
    }

    public PrdctInfoDto getPrdctInfo(SearchPrdctDto searchPrdctDto) throws Exception {
        return prdctDao.getPrdctInfo(searchPrdctDto);
    }

}
