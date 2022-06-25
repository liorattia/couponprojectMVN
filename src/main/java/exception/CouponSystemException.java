package exception;

public class CouponSystemException extends Exception{
    public CouponSystemException(String msg){

        super(msg);
    }

    public CouponSystemException(LayerMsg layerMsg){
        super(layerMsg.getMsg());
    }

    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getMsg());
    }
}
