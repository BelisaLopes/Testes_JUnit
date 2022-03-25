import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContactsManager {

    private List<Contact> contacts;
    private HashMap<String, List<Contact>> labels;

    public ContactsManager() {
        contacts = new LinkedList<>();
        labels = new HashMap<>(200);
    }

    public List<String> getLabels() {
        return new ArrayList<>(labels.keySet());
    }

    public void addLabel(String label) {
        this.labels.put(label.toLowerCase(), new LinkedList<>());
    }

    public void removeLabel(String label) {
        this.labels.remove(label.toLowerCase());
    }

    public List<Contact> getContacts(String... labels) {
        if (labels.length == 0) return contacts;

        var contactsFound = new LinkedList<Contact>(); //lista auxiliar

        for (var contact : contacts) {
            var isContactPresentInAllLabels = true;

            for (var label : labels) {
                var labelLowerCase = label.toLowerCase();
                if (! this.labels.containsKey(labelLowerCase)) {
                    isContactPresentInAllLabels = false;
                    break;
                }

                var contactsLabel = this.labels.get(labelLowerCase);

                if (! contactsLabel.contains(contact)) {
                    isContactPresentInAllLabels = false;
                    break;
                }
            }

            if (isContactPresentInAllLabels) {
                contactsFound.add(contact);
            }
        }

        return contactsFound;
    }
    public List<Contact> search(String term, String... labels) {
        if (labels.length == 0 && term.trim().isBlank()) return contacts; //se não existir label e não houver pesquisa de termos

        List<String> searchingLabels;//lista de pesquisa de labels

        if (labels.length == 0) {
            searchingLabels = this.getLabels();
        } else {
            searchingLabels = Arrays.stream(labels).collect(Collectors.toList());
        }

        var contactsFound = new LinkedList<Contact>();

        for (var label : searchingLabels) {
            var labelLowerCase = label.toLowerCase();

            if (! this.labels.containsKey(labelLowerCase)) continue;

            var contacts = this.labels.get(labelLowerCase);
            for (var contact : contacts) {
                if (contact.matches(term) && !contactsFound.contains(contact)) {
                    contactsFound.add(contact);
                }
            }
        }

        return contactsFound;
    }
    public void addContact(Contact contact, String... labels) {
        Predicate<Contact> duplicate = c -> Objects.equals(c.getPhone(), contact.getPhone())
                && Objects.equals(c.getEmail(), contact.getEmail());
        //se o duplicado e o contacto passado por parametro, têm o mesmo telefone ou o mesmo email

        if (contacts.stream().noneMatch(duplicate)) contacts.add(contact); //se não existem duplicados

        if (labels.length == 0) return;

        for (var label : labels) {
            if (!this.labels.containsKey(label)) { //se não contém a label
                this.labels.put(label, new LinkedList<>()); //adicionar a label nova do contacto
            }

            var contactsLabel = this.labels.get(label); //ir buscar a label
            if (!contacts.stream().noneMatch(duplicate)) {
                contactsLabel.add(contact);
            }
        }

        // add contact and associate it with the labels, if any
        // DO NOT ALLOW TO ADD DUPLICATED CONTACTS (same phone and/or email)
    }
    public void removeContact(Contact contact) {
        contacts.remove(contact); //lista de contactos, remover o contacto
        labels.values().forEach(contacts -> contacts.remove(contact));//para cada valor das labels pertencentes àquele
        // contacto, remover aquele contacto da tabela de hash
    }
    public int size() {
        return contacts.size();
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }
}
