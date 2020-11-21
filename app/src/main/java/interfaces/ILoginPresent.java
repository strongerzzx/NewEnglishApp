package interfaces;

import presenters.LoginPresent;

public interface ILoginPresent {

    void doLogin(LoginPresent.LoginCallback loginCallback);

    void regLoginCallback(ILoginCallback callback);

    void unReLoginCallback(ILoginCallback callback);
}
