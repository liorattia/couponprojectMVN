package exception;

public enum LayerMsg {
    CONNECTION_POOL("Something went wrong inside Connection Pool"),
    JDBC_UTILS("Something went wrong inside JDBC Utils"),
    DAO("Something wend wrong inside DAO Layer");


    private String msg;

    LayerMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }


}
