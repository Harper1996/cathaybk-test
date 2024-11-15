package tw.idv.harper.cathaybktest.core.pojo;

import lombok.Data;

@Data
public class Core {

    private static final long serialVersionUID = 1457755989409740329L;
    private boolean successful;
    private String message;
    private Object data;
}
