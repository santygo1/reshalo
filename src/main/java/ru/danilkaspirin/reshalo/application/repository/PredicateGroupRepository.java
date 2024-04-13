package ru.danilkaspirin.reshalo.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.danilkaspirin.reshalo.application.models.PredicateGroupModel;

import java.util.List;

@Repository
public interface PredicateGroupRepository extends JpaRepository<PredicateGroupModel, Long> {

    @Query("from PredicateGroupModel pg " +
            "left join fetch pg.predicates " +
            "left  join fetch pg.breakage " +
            "where pg.detailType.typeDef = :detailType")
    List<PredicateGroupModel> findByDetailTypeOrderByWeightDesc(@Param("detailType") String detailType);
}
