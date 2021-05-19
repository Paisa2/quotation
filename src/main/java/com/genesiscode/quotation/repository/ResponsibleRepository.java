package com.genesiscode.quotation.repository;
import com.genesiscode.quotation.domain.Responsible;
import com.genesiscode.quotation.dto.ResponsibleView;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {

    @Query("select r.email from Responsible r where r.email = :email")
    Optional<Responsible> findByEmail(@Param("email") String email);

    @Modifying
    @Query("update Responsible r " +
            "set r.enabled = true " +
            "where r.email = :email")
    void enableResponsible(@Param("email") String email);

    @Query(value =  "select new com.genesiscode.quotation.dto.ResponsibleView(r.name, r.lastName, r.email, r.enabled, rol.name) " +
                    "from Responsible r, Role rol " +
                    "where r.id = rol.id")
    List<ResponsibleView> findAllResponsible();
}
