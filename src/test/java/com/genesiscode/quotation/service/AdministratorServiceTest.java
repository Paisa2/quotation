package com.genesiscode.quotation.service;
import com.genesiscode.quotation.domain.unit.AdministrativeUnit;
import com.genesiscode.quotation.repository.UnitAdministrativeRepository;
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

    @Mock
    private UnitAdministrativeRepository repository;
    private AdministratorService serviceUnderTest;

    private static final Long VALID_ID = 1L;
    private static final Long INVALID_ID = 100L;
    private static final String ENTITY_NAME = "Fake Name";
    private static final int ONE_INVOCATION = 1;

    private AdministrativeUnit getUnitAdministrative() {
        AdministrativeUnit unit = new AdministrativeUnit();
        unit.setId(VALID_ID);
        unit.setName(ENTITY_NAME);
        return unit;
    }
    @BeforeEach
    void setUp() {
        this.serviceUnderTest = new AdministratorService(repository);
    }

    @Nested
    @DisplayName("Create Unit Administrative")
    class CreateAdministrativeUnitMethodTest {

        @Test
        @DisplayName("cannot create when Unit Administrative is null")
        void cannotCreateWhenUnitAdministrativeIsNull() {
            //GIVEN

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                    () -> serviceUnderTest.createUnitAdministrative(null));

            //THEN
            assertAll(
                () -> assertThat(thrownException)
                        .isExactlyInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("Unit Administrative must not be null"),
                () -> then(repository).should(never()).save(any())
            );
        }


        @Test
        @DisplayName("can create the unit administrative")
        void canCreateTheUnitAdministrative() {
            //GIVEN
            AdministrativeUnit unit = getUnitAdministrative();
            //WHEN
            serviceUnderTest.createUnitAdministrative(unit);
            //THEN
            ArgumentCaptor<AdministrativeUnit> repositoryCaptor = ArgumentCaptor.forClass(AdministrativeUnit.class);
            assertAll(
                () -> verify(repository, times(ONE_INVOCATION)).save(repositoryCaptor.capture()),
                () -> { AdministrativeUnit unitCaptured = repositoryCaptor.getValue();
                        assertThat(unitCaptured).isEqualTo(unit); }
            );
        }
    }
    
    @Nested
    @DisplayName("Get Units Administrative")
    class GetUnitsAdministrativeMethodTest {

        @Test
        @DisplayName("can get units administrative")
        void canGetUnitsAdministrative() {
            //GIVEN
            //WHEN
            serviceUnderTest.getUnitsAdministrative();
            //THEN
            verify(repository, times(ONE_INVOCATION)).findAll();
        }
    }

    @Nested
    @DisplayName("Get Unit Administrative By ID")
    class GetAdministrativeUnit {

        @Test
        @DisplayName("thrown exception when ID is null")
        void thrownExceptionWhenIdIsNull() {
            //GIVEN

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                    () -> serviceUnderTest.getUnitAdministrativeById(null));

            //THEN
            assertAll(
                () -> assertThat(thrownException)
                        .isExactlyInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("Id most not be null"),
                () -> verify(repository, never()).findById(anyLong())
            );
        }

        @Test
        @DisplayName("return a Unit Administrative with Id Valid")
        void returnAUnitAdministrativeWithIdValid() {
            //GIVEN
            given(repository.findById(VALID_ID)).willReturn(Optional.of(getUnitAdministrative()));

            //WHEN
            serviceUnderTest.getUnitAdministrativeById(VALID_ID);

            //THEN
            verify(repository, times(ONE_INVOCATION)).findById(VALID_ID);
        }

        @Test
        @DisplayName("thrown a Exception when does exists Unit Administrative")
        void thrownAExceptionWhenDoesExistsUnitAdministrative() {
            //GIVEN
            given(repository.findById(INVALID_ID)).willReturn(Optional.empty());

            //WHEN
            Exception thrownException = assertThrows(IllegalStateException.class,
                    () -> serviceUnderTest.getUnitAdministrativeById(INVALID_ID));
            //THEN
            assertAll(
                    () -> verify(repository,times(ONE_INVOCATION)).findById(INVALID_ID),
                    () -> assertThat(thrownException)
                            .isExactlyInstanceOf(IllegalStateException.class)
                            .hasMessageContaining("No exists this unit administrative with id " + INVALID_ID)
            );
        }
    }

}