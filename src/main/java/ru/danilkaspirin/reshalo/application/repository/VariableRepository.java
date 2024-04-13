package ru.danilkaspirin.reshalo.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilkaspirin.reshalo.application.models.VariableModel;

@Repository
public interface VariableRepository extends JpaRepository<VariableModel, String> {
}
