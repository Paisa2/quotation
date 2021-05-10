package com.genesiscode.quotation.repository;

import com.genesiscode.quotation.domain.user.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {

    Optional<Responsible> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Responsible r " +
            "set r.enabled = true " +
            "where r.email = ?1")
    int enableResponsible(String email);
}
