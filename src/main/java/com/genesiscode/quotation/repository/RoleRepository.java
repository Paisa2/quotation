package com.genesiscode.quotation.repository;
import com.genesiscode.quotation.domain.*;
import com.genesiscode.quotation.dto.RoleView;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query("update Role role set role.permission = :permissions where role.id = :id")
    void updatePermissionById(@Param("id") Long id, @Param("permissions") List<Permission> permissions);

    @Query("SELECT CASE WHEN COUNT(role) > 0 " +
            "THEN true " +
            "ELSE false END " +
            "FROM Role role " +
            "WHERE role.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("select new com.genesiscode.quotation.dto.RoleView" +
                                                "(role.id, role.name) " +
            "from Role role")
    List<RoleView> getListRole();
}
