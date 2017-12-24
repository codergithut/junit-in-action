package tianjian.section04;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianjian on 2017/12/21.
 */
public class MockAccountManager implements AccountManager {
    private Map<String, Account> accounts = new HashMap<String, Account>();

    @Override
    public Account findAccountForUser(String userId) {
        return this.accounts.get(userId);
    }

    @Override
    public void updateAccount(Account account) {

    }

    public void addAccount(String userId, Account account) {
        this.accounts.put(userId, account);
    }
}
