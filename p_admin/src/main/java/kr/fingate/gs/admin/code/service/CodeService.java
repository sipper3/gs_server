package kr.fingate.gs.admin.code.service;

import kr.fingate.gs.admin.code.dao.CodeDao;
import kr.fingate.gs.admin.code.dto.DispCodeDto;
import kr.fingate.gs.comon.consts.enums.RedisKey;
import kr.fingate.gs.comon.util.ObjectUtil;
import kr.fingate.gs.comon.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeService {

    final RedisUtil redisUtil;
    private final CodeDao codeDao;

    public Map<String, Object> selDispCode(List<String> codeInitList) throws Exception {

        Map<String, Object> data = new HashMap<String, Object>();
        List<DispCodeDto> list = codeDao.selDispCode(codeInitList);

        if(ObjectUtil.isEmpty(list)) return null;

        for(String codeInit : codeInitList) {
            List<DispCodeDto> collectList = list.stream().filter(a -> a.getCodeInit().equals(codeInit)).collect(Collectors.toList());
            data.put(codeInit, collectList);

            redisUtil.setHashData(redisUtil.getKey(RedisKey.GS_COMM_CODE, "code"), codeInit, collectList);
        }

        return data;
    }
}
