package alpha.olsp.usrsvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import java.util.UUID;

@Entity
public class State {

    @Id
    @Column(name = "state_id")
    private String stateID = UUID.randomUUID().toString();

    private String stateName;

    public State() {
    }

    public State(String stateID, String stateName) {
        this.stateID = stateID;
        this.stateName = stateName;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}