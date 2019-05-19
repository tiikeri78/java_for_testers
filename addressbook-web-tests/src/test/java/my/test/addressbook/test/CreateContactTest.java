package my.test.addressbook.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import my.test.addressbook.model.ContactData;
import my.test.addressbook.model.Contacts;
import my.test.addressbook.model.GroupData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void createContactTests(ContactData contact) {
        app.goTo().groupPage();
        if (app.group().set().size() == 0) {
            app.group().create((new GroupData()).withName("Test1").withHeader("testers").withFooter("test"));
        }

        app.goTo().contactPage();
        Contacts before = app.contact().set();
        //File photo = new File("src/test/resources/frog.jpg");
        //ContactData contact = new ContactData().withFirstname("Ti")
        //      .withLastname("Pin").withAddress("Lunapark").withMobileNumber("+987954389876").withEmail("12396@mail.ry").withGroup("Test1").withPhoto(photo);
        app.contact().create(contact, true);
        app.goTo().contactPage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().set();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
