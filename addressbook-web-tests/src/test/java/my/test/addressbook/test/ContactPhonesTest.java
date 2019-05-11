package my.test.addressbook.test;

import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonesTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().set().size() == 0) {
            app.goTo().groupPage();
            if (app.group().set().size() == 0) {
                app.group().create(new GroupData().withName("Testers").withHeader("testers").withFooter("test5"));
            }
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Zelda").withLastname("Smith").withAddress("Nevada").withMobileNumber("+195432567").withEmail("krasotka@mail.ry")
                    .withGroup("Testers"), true);
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().contactPage();
        ContactData contact = app.contact().set().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones (ContactData contact){
        return Arrays.asList(contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber())
                .stream().filter((s) -> ! s.equals("")).map(ContactPhonesTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("-()", "");
    }
}
