package com.genesiscode.quotation.service;
import com.genesiscode.quotation.domain.unit.DirectionUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.repository.*;
import com.genesiscode.quotation.security.RoleResponsible;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

@DisplayName("Responsible Service Tests")
@ExtendWith(MockitoExtension.class)
class ResponsibleServiceTest {

    @Mock
    private DirectionUnitRepository directionUnitRepository;
    @Mock
    private ResponsibleRepository responsibleRepository;
    private ResponsibleService serviceUnderTest;


    private static final String ENTITY_NAME = "Fake Name";
    private static final String ENTITY_LAST_NAME = "Fake Last Name";
    private static final String ENTITY_EMAIL = "Fake Email";
    private static final int ONE_INVOCATION = 1;

    private Responsible getResponsible() {
        Responsible responsible = new Responsible();
        responsible.setName(ENTITY_NAME);
        responsible.setLastName(ENTITY_LAST_NAME);
        responsible.setEmail(ENTITY_EMAIL);
        return responsible;
    }

    private DirectionUnit getDirectionUnit() {
        DirectionUnit unit = new DirectionUnit();
        unit.setName(ENTITY_NAME);
        return unit;
    }

    @BeforeEach
    void setUp() {
        this.serviceUnderTest = new ResponsibleService(directionUnitRepository, responsibleRepository);
    }

    @Nested
    @DisplayName("Create Direction Unit")
    class CreateDirectionUnitMethodTest {

        @Test
        @DisplayName("thrown exception when direction unit is null")
        void thrownExceptionWhenDirectionUnitIsNull() {
            //GIVEN

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                                            () -> serviceUnderTest.createDirectionUnit(null));
            //THEN
            assertAll(
                () -> assertThat(thrownException)
                            .isExactlyInstanceOf(IllegalStateException.class)
                            .hasMessageContaining("Direction Unit cannot be null"),
                () -> then(directionUnitRepository).should(never()).save(any(DirectionUnit.class))
            );
        }

        @Test
        @DisplayName("can create direction unit")
        void canCreateDirectionUnit() {
            //GIVEN
            DirectionUnit unit = getDirectionUnit();

            //WHEN
            serviceUnderTest.createDirectionUnit(unit);

            //THEN
            ArgumentCaptor<DirectionUnit> argumentCaptor = ArgumentCaptor.forClass(DirectionUnit.class);
            assertAll(
                () -> verify(directionUnitRepository, times(ONE_INVOCATION)).save(argumentCaptor.capture()),
                () -> { DirectionUnit unitCaptured = argumentCaptor.getValue();
                        assertThat(unitCaptured).isEqualTo(unit); }
            );
        }
    }

    @Nested
    @DisplayName("Create Head of Direction Unit")
    class CreateHeadOfDirectionUnitMethodTest {

        @Test
        @DisplayName("thrown exception when responsible is null")
        void thrownExceptionWhenResponsibleIsNull() {
            //GIVEN

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                    () -> serviceUnderTest.createHeadOfDirectionUnit(null));
            //THEN
            assertAll(
                () -> assertThat(thrownException)
                        .isExactlyInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("Responsible cannot be null"),
                () -> then(responsibleRepository).should(never()).save(any(Responsible.class))
            );
        }

        @Test
        @DisplayName("thrown exception when role is incorrect")
        void thrownExceptionWhenRoleIsIncorrect() {
            //GIVEN
            RoleResponsible roleInvalid = RoleResponsible.HEAD_OF_DEPENDENCY_EXPENSE_UNIT;
            Responsible responsible = getResponsible();
            responsible.setRole(roleInvalid);

            //WHEN
            Exception thrownException = assertThrows(IllegalArgumentException.class,
                                    () -> serviceUnderTest.createHeadOfDirectionUnit(responsible));
            //THEN
            assertAll(
                () -> assertThat(thrownException)
                        .isExactlyInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("You couldn't create this Role"),
                () -> then(responsibleRepository).should(never()).save(any(Responsible.class))
            );
        }

        @Test
        @DisplayName("can create role with its data correct")
        void canCreateRoleWithItsDataCorrect() {
            //GIVEN
            RoleResponsible roleValid = RoleResponsible.HEAD_OF_DIRECTION;
            Responsible responsible = getResponsible();
            responsible.setRole(roleValid);

            //WHEN
            serviceUnderTest.createHeadOfDirectionUnit(responsible);

            //THEN
            ArgumentCaptor<Responsible> argumentCaptor = ArgumentCaptor.forClass(Responsible.class);
            assertAll(
                () -> verify(responsibleRepository, times(ONE_INVOCATION)).save(argumentCaptor.capture()),
                () -> {
                    Responsible responsibleCaptured = argumentCaptor.getValue();
                    assertThat(responsibleCaptured).isEqualTo(responsible);
                }
            );
        }
    }

}