package kr.fingate.gs.core.consts.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS_TOKEN("C-Authm-Token","AccessToken"),
    REFRESH_TOKEN("Z-Authm-Token","RefreshToken"),
    LICENCE_TOKEN("I-Authm-Token","LicenceToken");

    String header;
    String subject;
}