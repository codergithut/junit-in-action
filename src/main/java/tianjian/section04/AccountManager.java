package tianjian.section04;

/**
 * Created by tianjian on 2017/12/21.
 */
public interface AccountManager {
    Account findAccountForUser(String userId);
    void updateAccount(Account account);
}
