package alpha.olsp.usrsvc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;

@Entity
public class Admin extends User{
    public Admin() {
        super();
    }

    public Admin(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_ADMIN_ADDRESS"))
    private Address address;
}
