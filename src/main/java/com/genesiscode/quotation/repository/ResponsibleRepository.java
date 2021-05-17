package com.genesiscode.quotation.repository;

import com.genesiscode.quotation.domain.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {

    @Query("select r.email from Responsible r where r.email = :email")
    Optional<Responsible> findByEmail(@Param("email") String email);

    @Modifying
    @Query("update Responsible r " +
            "set r.enabled = true " +
            "where r.email = :email")
    void enableResponsible(@Param("email") String email);

}
