package alpha.olsp.usrsvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin extends User{
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_ADMIN_ADDRESS"))
    @JsonProperty("address")
    private Address address;

    @Override
    public String toString() {
        return "Admin{" +
                "address=" + address +
                "} " + super.toString();
    }
}
