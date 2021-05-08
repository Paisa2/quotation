package com.genesiscode.quotation.service;
import com.genesiscode.quotation.domain.unit.AdministrativeUnit;
import com.genesiscode.quotation.domain.user.Responsible;
import com.genesiscode.quotation.repository.ResponsibleRepository;
import com.genesiscode.quotation.repository.AdministrativeUnitRepository;
import com.genesiscode.quotation.security.RoleResponsible;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import java.util.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("Administrator Service Tests")
@ExtendWith(MockitoExtension.class)
class AdministratorServiceTest {

    @Mock private AdministrativeUnitRepository administrativeUnitRepository;
    @Captor ArgumentCaptor<AdministrativeUnit> captorAdministrativeUnit;

    @Mock private ResponsibleRepository responsibleRepository;
    @Captor ArgumentCaptor<Responsible> captorResponsible;

    private AdministratorService serviceUnderTest;

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = 100L;
    private static final String ADMINISTRATIVE_UNIT_NAME = "Fake Name";

    private static final String RESPONSIBLE_NAME = "Fake Name";
    private static final String RESPONSIBLE_LAST_NAME = "Fake Last Name";
    private static final String RESPONSIBLE_EMAIL = "Fake Email";

    private static final int ONE_INVOCATION = 1;

    private AdministrativeUnit getAdministrativeUnit() {
        AdministrativeUnit unit = new AdministrativeUnit();
        unit.setId(VALID_ID);
        unit.setName(ADMINISTRATIVE_UNIT_NAME);
        return unit;
    }
    private Responsible getResponsible(RoleResponsible role) {
        Responsible responsible = new Responsible();
        responsible.setName(RESPONSIBLE_NAME);
        responsible.setLastName(RESPONSIBLE_LAST_NAME);
        responsible.setEmail(RESPONSIBLE_EMAIL);
        responsible.setRole(role);
        return responsible;
    }

    @BeforeEach
    void setUp() {
        this.serviceUnderTest = new AdministratorService(administrativeUnitRepository, responsibleRepository);
    }

    @Nested
    @DisplayName("Create Administrative Unit")
    class CreateAdministrativeUnitMethodTest {

        @Test
        @DisplayName("thrown exception when administrative unit is null")
        void thrownExceptionWhenAdministrativeUnitIsNull() {
            //GIVEN

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                    () -> serviceUnderTest.createAdministrativeUnit(null));

            //THEN
            assertAll(
                    () -> assertThat(thrownException)
                            .isExactlyInstanceOf(IllegalStateException.class)
                            .hasMessageContaining("Administrative Unit must not be null"),
                    () -> then(administrativeUnitRepository).should(never()).save(any(AdministrativeUnit.class))
            );
        }

        @Test
        @DisplayName("can create the unit administrative")
        void canCreateTheUnitAdministrative() {
            //GIVEN
            AdministrativeUnit unit = getAdministrativeUnit();

            //WHEN
            serviceUnderTest.createAdministrativeUnit(unit);

            //THEN
            assertAll(
                () -> verify(administrativeUnitRepository, times(ONE_INVOCATION)).save(captorAdministrativeUnit.capture()),
                () -> assertThat(captorAdministrativeUnit.getValue()).isEqualTo(unit)
            );
        }
    }

    @Nested
    @DisplayName("Create Responsible Of Administrative Unit")
    class CreateResponsibleOfAdministrativeUnit {

        @Test
        @DisplayName("thrown exception when responsible is null")
        void thrownExceptionWhenResponsibleIsNull() {
            //GIVEN

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                                        () -> serviceUnderTest.createHeadOfAdministrativeUnit(null));
            //THEN
            assertAll(
                () -> assertThat(thrownException)
                            .isExactlyInstanceOf(IllegalStateException.class)
                            .hasMessageContaining("Responsible must not be null"),
                () -> then(responsibleRepository).should(never()).save(any(Responsible.class))
            );
        }

        @Test
        @DisplayName("thrown exception when role is incorrect")
        void thrownExceptionWhenRoleIsIncorrect() {
            //GIVEN
            RoleResponsible incorrectRole = RoleResponsible.HEAD_OF_DEPENDENCY_EXPENSE_UNIT;
            Responsible responsible = getResponsible(incorrectRole);

            //WHEN
            Exception thrownException = assertThrows(IllegalArgumentException.class,
                                    () -> serviceUnderTest.createHeadOfAdministrativeUnit(responsible));
            //THEN
            assertAll(
                () -> assertThat(thrownException)
                            .isExactlyInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("You couldn't create this Role"),
                () -> then(responsibleRepository).should(never()).save(any(Responsible.class))
            );
        }

        @Test
        @DisplayName("can create responsible when data is correct")
        void canCreateResponsibleWhenDataIsCorrect() {
            //GIVEN
            RoleResponsible correctRole = RoleResponsible.HEAD_OF_ADMINISTRATIVE_UNIT;
            Responsible responsibleGiven = getResponsible(correctRole);

            //WHEN
            serviceUnderTest.createHeadOfAdministrativeUnit(responsibleGiven);

            //THEN
            assertAll(
                () -> then(responsibleRepository).should(times(ONE_INVOCATION)).save(captorResponsible.capture()),
                () -> assertThat(captorResponsible.getValue()).isEqualTo(responsibleGiven)
            );
        }

    }
    
    @Nested
    @DisplayName("Get Administrative Units")
    class GetAdministrativeUnitsMethodTest {

        @Test
        @DisplayName("can get all administrative units")
        void canGetAllAdministrativeUnits() {
            //GIVEN

            //WHEN
            serviceUnderTest.getAdministrativeUnits();

            //THEN
            verify(administrativeUnitRepository, times(ONE_INVOCATION)).findAll();
        }
    }

    @Nested
    @DisplayName("Get Administrative Unit By ID")
    class GetAdministrativeUnit {

        @Test
        @DisplayName("thrown exception when ID is null")
        void thrownExceptionWhenIdIsNull() {
            //GIVEN

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                    () -> serviceUnderTest.getAdministrativeUnitById(null));

            //THEN
            assertAll(
                () -> assertThat(thrownException)
                        .isExactlyInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("Id most not be null"),
                () -> verify(administrativeUnitRepository, never()).findById(anyLong())
            );
        }

        @Test
        @DisplayName("return a Unit Administrative with Id Valid")
        void returnAUnitAdministrativeWithIdValid() {
            //GIVEN
            given(administrativeUnitRepository.findById(VALID_ID)).willReturn(Optional.of(getAdministrativeUnit()));

            //WHEN
            serviceUnderTest.getAdministrativeUnitById(VALID_ID);

            //THEN
            verify(administrativeUnitRepository, times(ONE_INVOCATION)).findById(VALID_ID);
        }

        @Test
        @DisplayName("thrown a Exception when does exists Unit Administrative")
        void thrownAExceptionWhenDoesExistsUnitAdministrative() {
            //GIVEN
            given(administrativeUnitRepository.findById(INVALID_ID)).willReturn(Optional.empty());

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                    () -> serviceUnderTest.getAdministrativeUnitById(INVALID_ID));
            //THEN
            assertAll(
                    () -> verify(administrativeUnitRepository,times(ONE_INVOCATION)).findById(INVALID_ID),
                    () -> assertThat(thrownException)
                            .isExactlyInstanceOf(IllegalStateException.class)
                            .hasMessageContaining("No exists this unit administrative with id " + INVALID_ID)
            );
        }
    }


}