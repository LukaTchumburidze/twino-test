package org.twino.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.twino.model.Operator;

@Repository
public interface OperatorRepository extends CrudRepository<Operator, Long> {
    Operator findByUsername (String username);
}
