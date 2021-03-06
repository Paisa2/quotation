package com.genesiscode.quotation.repository;

import com.genesiscode.quotation.domain.user.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {

}
