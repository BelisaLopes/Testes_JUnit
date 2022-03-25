import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTestCase {
    //@Test ------> NA AULA
    /*public void testCreateContact() {
        var contact = new Contact("foo");
        assertEquals("foo", contact.getFirstName());
    }*/

    /*public void testConstructor2() {
        var contact = new Contact("Manuel", "Silva");
        assertEquals("Silva", contact.getFirstName());
        assertEquals("Manuel", contact.getLastName());
    }*/

    @Test public void testCreateContact() {
        var contact = new Contact("foo", "bar", "912 345 678");
        assertEquals("foo", contact.getFirstName());
        assertEquals("bar", contact.getLastName());
        assertEquals("912 345 678", contact.getPhone());
    }

    @Test public void testCreateContactThatFails() {
        var contact = new Contact("foo", "912 345 678");
        assertEquals("foo", contact.getFirstName());
        assertNotNull(contact.getLastName(), "o último nome não está definido");
        assertEquals("912 345 678", contact.getPhone());
    }

    @Test
    public void testRemoveContact() {
        var contact = new Contact("foo", "931 456 907");
        ContactsManager cm = new ContactsManager();
        cm.addContact(contact);
        assertEquals(1, cm.size());
        cm.removeContact(contact);

        assertTrue(cm.isEmpty(), "<A lista está vazia>");
    }
    @Test
    public void testTryRemoveNonexistentContact() {
        ContactsManager cm = new ContactsManager();

        var foobar = new Contact("Foo", "Bar", "928 032 179", "foo@bar.test");
        var dummy = new Contact("Mr.", "Dummy","964 475 145", "mr@dummy.test");
        cm.addContact(foobar);
        assertEquals(1, cm.size());
        cm.removeContact(dummy);
        assertFalse(cm.isEmpty(), "<A lista não está vazia>");
    }

        @Test
        public void testDontRemoveUnlessIsSameContact() {
            ContactsManager cm = new ContactsManager();

            var foo = new Contact("foo", "91X ABC DEF");
            var bar = new Contact("bar", "91X ABC DEF");
            cm.addContact(foo);
            assertEquals(1, cm.size());
            cm.removeContact(bar);
            assertFalse(cm.isEmpty(), "<A lista não está vazia>");
        }

}
