package tianjian.section04;

import sun.rmi.runtime.Log;

import javax.security.auth.login.Configuration;

/**
 * Created by tianjian on 2017/12/24.
 */
public class DefaultAccountManager implements AccountManager {
    private Log logger;

    private Configuration configuration;

    public DefaultAccountManager(Log logger, Configuration configuration) {
        this.logger = logger;
        this.configuration = configuration;
    }

    @Override
    public Account findAccountForUser(String userId) {
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }
}
