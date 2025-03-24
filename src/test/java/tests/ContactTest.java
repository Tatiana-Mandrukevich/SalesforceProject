package tests;

import objects.Account;
import objects.Contact;
import org.testng.annotations.Test;

public class ContactTest extends BaseTest{

    @Test(description = "Create a new contact and check contact details in the table")
    public void createContactAndCheckDetailsInTable() {
        random.setSeed(System.currentTimeMillis());

        Account account = new Account();
        account.setAccountName("account" + random.nextInt(100));
        account.setWebsite("website");
        account.setType("Inversor");
        account.setPhone("80296193710");
        account.setDescription("test");

        Contact contact = new Contact();
        contact.setFirstName("FirstNameValue" + random.nextInt(100));
        contact.setLastName("LastNameValue" + random.nextInt(100));
        contact.setTitle("TitleValue" + random.nextInt(100));
        contact.setDescription("DescriptionValue" + random.nextInt(100));
        contact.setPhone("805553322" + random.nextInt(100));
        contact.setEmail("email.value" + random.nextInt(100) + "@test.test");

        loginPage.openPage(LOGIN_URL)
                .login(username, password);
        newAccountModalPage.openPage(NEW_ACCOUNT_MODAL_URL)
                .openPage(NEW_ACCOUNT_MODAL_URL)//так сделала тест стабильным, так как waiter не помогал
                .createNewAccount(account);
        newContactModalPage.openPage(NEW_CONTACT_MODAL_URL)
                .openPage(NEW_CONTACT_MODAL_URL)
                .createNewContact(contact, account);
        contactListPage.openPage(CONTACT_LIST_URL);
        softAssert.assertEquals(contactListPage.getExistAccountName(account.getAccountName()), account.getAccountName());
        softAssert.assertEquals(contactListPage.getExistPhoneNumberByAccountName(account.getAccountName()), contact.getPhone());
        softAssert.assertEquals(contactListPage.getExistAccountOwnerByAccountName(account.getAccountName()), "TMand");
        softAssert.assertEquals(contactListPage.getExistNameByAccountName(account.getAccountName(), contact.getFirstName() + " " + contact.getLastName()), contact.getFirstName() + " " + contact.getLastName());
        softAssert.assertEquals(contactListPage.getExistEmailByAccountName(account.getAccountName()), contact.getEmail());
        softAssert.assertAll();
    }

    @Test(description = "Create a new contact and check contact details in the form")
    public void createContactAndCheckDetailsInForm() {
        Account account = new Account();
        account.setAccountName("account" + random.nextInt(50));
        account.setWebsite("website");
        account.setType("Inversor");
        account.setPhone("80296193710");
        account.setDescription("test");

        Contact contact = new Contact();
        contact.setFirstName("FirstNameValue" + random.nextInt(100));
        contact.setLastName("LastNameValue" + random.nextInt(100));
        contact.setTitle("TitleValue" + random.nextInt(100));
        contact.setDescription("DescriptionValue" + random.nextInt(100));
        contact.setPhone("805553322" + random.nextInt(100));
        contact.setEmail("email.value" + random.nextInt(100) + "@test.test");

        loginPage.openPage(LOGIN_URL)
                .login(username, password);
        newAccountModalPage.openPage(NEW_ACCOUNT_MODAL_URL)
                .openPage(NEW_ACCOUNT_MODAL_URL)//так сделала тест стабильным, так как waiter не помогал
                .createNewAccount(account);
        newContactModalPage.openPage(NEW_CONTACT_MODAL_URL)
                .openPage(NEW_CONTACT_MODAL_URL)
                .createNewContact(contact, account);
        contactListPage.openPage(CONTACT_LIST_URL)
                .clickOnContactName(account.getAccountName(), contact.getFirstName() + " " + contact.getLastName());
        softAssert.assertEquals(contactPage.getFieldValueByName("Name"), contact.getFirstName() + " " + contact.getLastName());
        softAssert.assertEquals(contactPage.getAccountNameValueByName("Account Name"), account.getAccountName());
        softAssert.assertEquals(contactPage.getFieldValueByName("Title"), contact.getTitle());
        softAssert.assertEquals(contactPage.getFieldValueByName("Description"), contact.getDescription());
        softAssert.assertEquals(contactPage.getFieldValueByName("Phone"), contact.getPhone());
        softAssert.assertEquals(contactPage.getFieldValueByName("Email"), contact.getEmail());
        softAssert.assertAll();
    }
}