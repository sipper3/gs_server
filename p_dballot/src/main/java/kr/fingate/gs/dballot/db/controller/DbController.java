package kr.fingate.gs.dballot.db.controller;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.dballot.db.dto.DbListDto;
import kr.fingate.gs.dballot.db.dto.DbSearchDto;
import kr.fingate.gs.dballot.db.service.DbService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/db")
@RestController
@RequiredArgsConstructor
public class DbController {

    final DbService dbService;

    /**
     * DB목록
     * @param dbSearchDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDbList", method = RequestMethod.POST)
    public PageInfoDto<DbListDto> getDbList(@RequestBody DbSearchDto dbSearchDto) throws Exception {

        if(StringUtils.isEmpty(dbSearchDto.getRegStDate()) || StringUtils.isEmpty(dbSearchDto.getRegEdDate())) {
            throw new BizException(BizError.MISSING_PARAMETER);
        }

        return new PageInfoDto<>(dbService.getDbList(dbSearchDto));
    }

}
