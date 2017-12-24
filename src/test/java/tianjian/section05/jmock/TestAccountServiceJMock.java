package tianjian.section05.jmock;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tianjian.section04.Account;
import tianjian.section04.AccountManager;
import tianjian.section04.AccountService;

import static org.junit.Assert.assertEquals;

/**
 * Created by tianjian on 2017/12/24.
 */
@RunWith(JMock.class)
public class TestAccountServiceJMock {
    private Mockery context = new JUnit4Mockery();
    private AccountManager mockAccountManager;
    @Before
    public void setUp() {
        mockAccountManager = context.mock(AccountManager.class);
    }
    @Test
    public void testTransferOK() {
        final Account senderAccount = new Account("1", 200);
        final Account beneficiaryAccount = new Account("2", 100);
        context.checking(new Expectations(){
            {
                oneOf(mockAccountManager).findAccountForUser("1");
                will(returnValue(senderAccount));
                oneOf(mockAccountManager).findAccountForUser("2");
                will(returnValue(beneficiaryAccount));

                oneOf(mockAccountManager).updateAccount(senderAccount);
                oneOf(mockAccountManager).updateAccount(beneficiaryAccount);
            }

        });

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);
        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());

    }
}
