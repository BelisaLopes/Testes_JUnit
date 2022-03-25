import java.util.Date;
import java.util.Objects;

public class Contact {

    private String firstName;
    private String lastName;
    private Date birthday;
    private String phone;
    private String email;

    public Contact(String firstName, String phone){
        this.firstName = firstName;
        this.phone = phone;
    }

    public Contact(String firstName, String lastName, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Contact(String firstName, String lastName, String phone, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Contact(String firstName, String lastName, String phone, String email, Date birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean matches(String term) {
        var termLowerCase = term.toLowerCase();
        if (termLowerCase.isBlank()) return false;

        if (this.firstName != null && this.firstName.toLowerCase().contains(term)) return true;
        if (this.lastName != null && this.lastName.toLowerCase().contains(term)) return true;
        if (this.phone != null && this.phone.toLowerCase().contains(term)) return true;
        if (this.email != null && this.email.toLowerCase().contains(term)) return true;
        if (this.birthday != null && this.birthday.toString().contains(term)) return true;

        return false;
    }

    @Override
    public boolean equals(Object o) { //verifica se os atributos(propriedades) são iguais
        if (!(o instanceof Contact)) return false;

        var another = (Contact) o;

        if (! Objects.equals(this.firstName, another.firstName)) return false;
        if (! Objects.equals(this.lastName, another.lastName)) return false;
        if (! Objects.equals(this.birthday, another.birthday)) return false;
        if (! Objects.equals(this.phone, another.phone)) return false;
        if (! Objects.equals(this.email, another.email)) return false;

        return true;
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday, phone, email);
    }
}
