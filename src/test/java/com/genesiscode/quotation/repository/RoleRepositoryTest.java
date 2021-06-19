package com.genesiscode.quotation.repository;
import com.genesiscode.quotation.domain.Role;
import com.genesiscode.quotation.dto.RoleView;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository underTest;

    @BeforeEach
    void setUp() {
        Role roleOne = new Role();
        roleOne.setName("rol one");

        Role roleTwo = new Role();
        roleTwo.setName("rol two");

        Role roleThree = new Role();
        roleThree.setName("rol three");

        underTest.saveAll(List.of(roleOne, roleTwo, roleThree));
    }

    @Nested
    @DisplayName("Exists by name")
    class ExistsByNameMethodTest {

        @Test
        @DisplayName("it returns true when role name exist")
        void itReturnsTrueWhenRoleNameExist() {
            //GIVEN
            String nameRoleOne = "rol one";
            String nameRoleThree = "rol three";

            //WHEN
            boolean resultExpectedOne = underTest.existsByName(nameRoleOne);
            boolean resultExpectedTwo = underTest.existsByName(nameRoleThree);

            //THEN
            assertAll(
                    () -> assertThat(resultExpectedOne).isTrue(),
                    () -> assertThat(resultExpectedTwo).isTrue()
            );
        }

        @Test
        @DisplayName("it returns false when role name does not exist")
        void itReturnsFalseWhenRoleNameDoesNotExist() {
            //GIVEN
            String nameRoleOne = "role one";
            String nameRoleThree = "role three";

            //WHEN
            boolean resultExpectedOne = underTest.existsByName(nameRoleOne);
            boolean resultExpectedTwo = underTest.existsByName(nameRoleThree);

            //THEN
            assertAll(
                () -> assertThat(resultExpectedOne).isFalse(),
                () -> assertThat(resultExpectedTwo).isFalse()
            );
        }
    }

    @Nested
    @DisplayName("Get list role")
    class GetListRoleMethodTest {

        @Test
        @DisplayName("it returns list RoleView objects")
        void itReturnsListRoleViewObjects() {
            //GIVEN
            RoleView roleViewExpectedOne = new RoleView(1L, "rol one");
            RoleView roleViewExpectedTwo = new RoleView(2L, "rol two");
            RoleView roleViewExpectedThree = new RoleView(3L, "rol three");

            //WHEN
            List<RoleView> listActual = underTest.getListRole();

            //THEN
            assertAll(
                () -> assertThat(listActual).isNotEmpty(),
                () -> assertThat(listActual.size()).isEqualTo(3),
                () -> assertThat(listActual).contains(roleViewExpectedTwo),
                () -> assertThat(listActual).containsExactly(roleViewExpectedOne, roleViewExpectedTwo, roleViewExpectedThree)
            );
        }
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();;
    }
}