package kr.fingate.gs.core.consts.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS_TOKEN("C-Auth-Token","AccessToken"),
    REFRESH_TOKEN("Z-Auth-Token","RefreshToken"),
    LICENCE_TOKEN("I-Auth-Token","LicenceToken");

    final String header;
    final String subject;
}