package interfaces;

public interface IRegPresent {
    void requestReg();

    void regRegCallback(IRegCallback callback);

    void unRegRegCallback(IRegCallback callback);

}
