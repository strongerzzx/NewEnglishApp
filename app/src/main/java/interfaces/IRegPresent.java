package interfaces;

import presenters.RegPresent;

public interface IRegPresent {
    void requestReg(RegPresent.onRegClickCallback regClickCallback);

    void regRegCallback(IRegCallback callback);

    void unRegRegCallback(IRegCallback callback);

}
