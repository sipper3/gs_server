package kr.fingate.gs.rpa.job.dto.ins;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeritzFire {

    private String[] aa = {"사용자코드(휴대폰)","비밀번호", "생년월일(8자리)"};
    private String[] script = {"userid", "password", "AuthNum"};
    @Getter
    private List<Map<String, String>> loginScript = new ArrayList<>();
    public void add(String value, int idx) {
        Map<String, String> login  = new HashMap<>();
        login.put("path", script[idx]);
        login.put("value", value);

        loginScript.add(login);
    }

}
