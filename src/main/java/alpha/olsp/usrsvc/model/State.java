package alpha.olsp.usrsvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class State {

    @Id
    @Column(name = "state_id")
    private String stateID = UUID.randomUUID().toString();

    private String stateName;
}