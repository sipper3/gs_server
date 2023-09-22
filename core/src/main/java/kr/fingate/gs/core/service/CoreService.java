package kr.fingate.gs.core.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoreService {

    /**
     * 접속한 사용자의 정보 조회
     */
    public static UserTokenDto getUserInfo() {
        try {
            Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(object instanceof UserTokenDto){
                return (UserTokenDto) object;
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper
                        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                        .convertValue(object, new TypeReference<>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new UserTokenDto();
        }
    }
}
