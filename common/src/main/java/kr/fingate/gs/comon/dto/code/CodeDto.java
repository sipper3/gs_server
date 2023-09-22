package kr.fingate.gs.comon.dto.code;

import kr.fingate.gs.comon.vo.code.CodeVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeDto extends CodeVO implements Cloneable {

    @Override
    public CodeDto clone() throws CloneNotSupportedException {
        return (CodeDto) super.clone();
    }
}
