import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ContactsManagerTestCase {

    private ContactsManager cm;

    @BeforeEach
    public void setUp() {
        cm = new ContactsManager(); //instanciar um novo contacts Manager
    }

    @Test
    public void testAddContact() {
        var contact = new Contact("foo", "917 252 063");
        cm.addContact(contact);
        assertEquals(1, cm.size());
        assertSame(contact, cm.getContacts().get(0));
    }

    @Test
    public void testDontAllowDuplicatedContacts() {
        var contact1 = new Contact("foo", "931 456 907");
        var contact2 = new Contact("foo", "931 456 907");// same contact! mas como são instancias diferentes, têm endereços de memória diferentes
        cm.addContact(contact1);
        cm.addContact(contact2);
        //assertSame(contact1, contact2); //pag 16
        // Help the developer by writing a friendly message
        assertEquals(1, cm.size(), "Duplicated contacts!");
    }

    @Test
    public void testDontAllowDuplicatedPhones() {
        var contact1 = new Contact("foo", "931 456 907");
        var contact2 = new Contact("bar", "931 456 907");

        cm.addContact(contact1);
        cm.addContact(contact2);

        assertEquals(1, cm.size(), "Duplicated phones!");
    }
}
