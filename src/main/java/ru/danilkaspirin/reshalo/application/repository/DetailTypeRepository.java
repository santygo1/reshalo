package ru.danilkaspirin.reshalo.application.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.danilkaspirin.reshalo.application.models.DetailTypeModel;

import java.util.Optional;

@Repository
public interface DetailTypeRepository extends JpaRepository<DetailTypeModel, Long> {

    @Query("select t from DetailTypeModel t " +
            "left join fetch t.characteristics ")
    Optional<DetailTypeModel> findByTypeDef(String typeDef);

}
