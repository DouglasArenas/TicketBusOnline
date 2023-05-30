import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void logIn() {
        // Implementation of logIn method
    }

    public void logOut() {
        // Implementation of logOut method
    }

    public void register() {
        // Implementation of register method
    }

    public Ticket ticket() {
        // Implementation of ticket method
        return null;
    }

    public List<Ticket> books() {
        // Implementation of books method
        return null;
    }
}
