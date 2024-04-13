package ru.danilkaspirin.reshalo.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.danilkaspirin.reshalo.application.models.BreakageModel;

import java.util.List;
import java.util.Set;

public interface BreakageRepository extends JpaRepository<BreakageModel, Long> {

    @Query("select t from BreakageModel t " +
            "left join fetch t.repairActions " +
            "left join fetch t.predicate " +
            "where t.id in (:ids)")
    List<BreakageModel> findAllByIdIn(List<Long> ids);
}
