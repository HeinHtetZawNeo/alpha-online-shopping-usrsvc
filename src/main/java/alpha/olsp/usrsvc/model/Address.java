package alpha.olsp.usrsvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @Column(name = "address_id")
    private String addressId = UUID.randomUUID().toString();

    private String addressLine1;
    private String addressLine2;
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    private State state;

    private String postalCode;

    public Address(String addressLine1, String addressLine2, String city, State state, String postalCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }
}