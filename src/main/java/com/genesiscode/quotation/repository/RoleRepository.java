package com.genesiscode.quotation.repository;
import com.genesiscode.quotation.domain.Permission;
import com.genesiscode.quotation.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query("update Role role set role.permission = :permissions where role.id = :id")
    void updatePermissionById(@Param("id") Long id, @Param("permissions") List<Permission> permissions);

    boolean existsByName(String name);
}
