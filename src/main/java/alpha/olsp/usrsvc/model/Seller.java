package alpha.olsp.usrsvc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;

@Entity
public class Seller extends User{
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_SELLER_ADDRESS"))
    private Address address;

    public Seller(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }

    public Seller() {
        super();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
