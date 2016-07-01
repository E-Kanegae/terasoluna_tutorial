package todo5.domain.model;

import lombok.Data;

@Data
public class Importance {

    private String importanceId;
    private String importanceNm;
    private boolean logicDeleteFlg;
}
