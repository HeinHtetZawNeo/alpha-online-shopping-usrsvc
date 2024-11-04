package alpha.olsp.usrsvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class State {

    @Id
    @Column(name = "state_id")
    private String stateID = UUID.randomUUID().toString();

    private String stateName;
}