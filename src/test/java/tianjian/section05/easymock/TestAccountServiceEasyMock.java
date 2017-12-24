package tianjian.section05.easymock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tianjian.section04.Account;
import tianjian.section04.AccountManager;
import tianjian.section04.AccountService;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by tianjian on 2017/12/24.
 */
public class TestAccountServiceEasyMock {
    private AccountManager mockAccountMannager;

    @Before
    public void setUp() {
        mockAccountMannager = createMock("mockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransFerOK() {
        Account senderAccount = new Account("1", 200);
        Account beneficaiaryAccount = new Account("2", 100);

        mockAccountMannager.updateAccount(senderAccount);
        mockAccountMannager.updateAccount(beneficaiaryAccount);

        expect(mockAccountMannager.findAccountForUser("1")).andReturn(senderAccount);
        expect(mockAccountMannager.findAccountForUser("2")).andReturn(beneficaiaryAccount);

        replay(mockAccountMannager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountMannager);
        accountService.transfer("1", "2", 50);
        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficaiaryAccount.getBalance());
    }

    @After
    public void tearDown() {
        verify(mockAccountMannager);
    }

}
