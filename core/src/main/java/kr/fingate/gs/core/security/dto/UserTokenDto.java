package kr.fingate.gs.core.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AccessToken에 저장될 Data 항목
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenDto {

    private long clientNo;

    private long userNo;

    private String userName;

    private String loginId;

}
