package alpha.olsp.usrsvc.repository;

import alpha.olsp.usrsvc.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State,String> {
}
