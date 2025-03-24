package tests;

import objects.Account;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest{

    @Test(description = "Create a new account and check account details in the table")
    public void createAccountAndCheckDetailsInTable() {
        Account account = new Account();
        account.setAccountName("account" + random.nextInt(50));
        account.setWebsite("website");
        account.setType("Inversor");
        account.setPhone("80296193710");
        account.setDescription("test");

        loginPage.openPage(LOGIN_URL)
                .login(username, password);
        newAccountModalPage.openPage(NEW_ACCOUNT_MODAL_URL)
                .openPage(NEW_ACCOUNT_MODAL_URL)//так сделала тест стабильным, так как waiter не помогал
                .createNewAccount(account);
        accountListPage.openPage(ACCOUNT_LIST_URL);
        softAssert.assertEquals(accountListPage.getExistAccountName(account.getAccountName()), account.getAccountName());
        softAssert.assertEquals(accountListPage.getExistPhoneNumberByAccountName(account.getAccountName()), account.getPhone());
        softAssert.assertEquals(accountListPage.getExistAccountOwnerByAccountName(account.getAccountName()), "TMand");
        softAssert.assertAll();
    }

    @Test(description = "Create a new account and check account details in the form")
    public void createAccountAndCheckDetailsInForm() {
        Account account = new Account();
        account.setAccountName("account" + random.nextInt(50));
        account.setWebsite("Text for website");
        account.setType("Cliente");
        account.setPhone("80296193710");
        account.setDescription("Text for description");

        loginPage.openPage(LOGIN_URL)
                .login(username, password);
        newAccountModalPage.openPage(NEW_ACCOUNT_MODAL_URL)
                .openPage(NEW_ACCOUNT_MODAL_URL)//так сделала тест стабильным, так как waiter не помогал
                .createNewAccount(account);
        accountListPage.openPage(ACCOUNT_LIST_URL)
                .clickOnAccountName(account.getAccountName());
        softAssert.assertEquals(accountPage.getFieldValueByName("Account Name"), account.getAccountName());
        softAssert.assertEquals(accountPage.getFieldValueByName("Website"), account.getWebsite());
        softAssert.assertEquals(accountPage.getFieldValueByName("Type"), account.getType());
        softAssert.assertEquals(accountPage.getFieldValueByName("Phone"), account.getPhone());
        softAssert.assertEquals(accountPage.getFieldValueByName("Description"), account.getDescription());
        softAssert.assertAll();
    }
}