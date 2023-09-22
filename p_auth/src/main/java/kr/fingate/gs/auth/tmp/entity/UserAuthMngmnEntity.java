package kr.fingate.gs.auth.tmp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthMngmnEntity {

    private long userNo;

    private String socialType;

    private String socialId;

    private String authmRole;

    private String authmEmailYn;

    private String authmTellnoYn;

    private Timestamp lastLoginDate;

    private Timestamp pswrdModDate;

}