package com.genesiscode.quotation.repository;
import com.genesiscode.quotation.domain.Responsible;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class ResponsibleRepositoryTest {

    private static final Long ID_VALID = 100L;
    private static final String NAME_VALID = "Jose Maria";
    private static final String LASTNAME_VALID = "Aguilar Chambi";
    private static final String EMAIL_VALID = "josmariaguilar@gmail.com";
    private static final String PASSWORD_VALID = "maria17";

    @Autowired
    private ResponsibleRepository underTest;

    private

    @BeforeEach
    void setUp() {
        Responsible juan = new Responsible();
        juan.setName("Juan Carlos");
        juan.setLastName("Arevalo");
        juan.setEmail("juan@gmail.com");
        juan.setPassword("arevalo");

        Responsible rafael = new Responsible();
        rafael.setName("Rafael");
        rafael.setLastName("Villca");
        rafael.setEmail("rafael@gmail.com");
        rafael.setPassword("villca");

        Responsible cynthia = new Responsible();
        cynthia.setName("Cynthia");
        cynthia.setLastName("Argote");
        cynthia.setEmail("cinthia@gmail.com");
        cynthia.setPassword("argote");

        Responsible alex = new Responsible();
        alex.setName("Alex");
        alex.setLastName("Osinaga");
        alex.setEmail("alex@gmail.com");
        alex.setPassword("osinaga");

        underTest.saveAll(List.of(juan, rafael, cynthia, alex));
    }

    @Nested
    @DisplayName("get responsible by email")
    class GetResponsibleByEmailMethodTest {

        @Test
        @DisplayName("it returns responsible with correct email ")
        void itReturnsResponsibleWithCorrectEmail() {
            //GIVEN
            Responsible expected = new Responsible();
            expected.setId(2L);
            expected.setName("Rafael");
            expected.setLastName("Villca");
            expected.setEmail("rafael@gmail.com");
            expected.setPassword("villca");

            //WHEN
            Optional<Responsible> responsibleActual =
                    underTest.getResponsibleByEmail("rafael@gmail.com");

            //THEN
            assertAll(
                () -> assertThat(responsibleActual.isPresent()).isTrue(),
                () -> assertThat(responsibleActual.get()).isEqualTo(expected)
            );
        }
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    void findAllResponsible() {
    }
}