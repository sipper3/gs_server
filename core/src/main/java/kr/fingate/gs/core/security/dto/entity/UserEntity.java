package kr.fingate.gs.core.security.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private long userNo;

    private long clientNo;

    private String loginEmail;

    private String loginPswrd;

    private String userName;

    private String userNiknm;

    private String cellTellNo;

    private String birthDate;

    private String genderType;

    private String dataStatus;

    private long regUserNo;

    private Timestamp regDate;

    private long modUserNo;

    private Timestamp modDate;

}