package com.example.RunningApp.trainingplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.RunningApp.Security.SecurityUtils;
import com.example.RunningApp.marathon.Marathon;
import com.example.RunningApp.marathon.MarathonRepository;
import com.example.RunningApp.trainingitem.TrainingItemService;
import com.example.RunningApp.trainingplan.dto.TrainingPlanListDTO;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class TrainingPlanServiceTest {
    @Mock
private TrainingPlanRepository repo;

@Mock
private MarathonRepository mRepo;

@Mock
private TrainingItemService itemService;

@Mock
private SecurityUtils securityUtils;

@InjectMocks
private TrainingPlanService service;

 @Test
    void getAll_whenUserHasNoPlans_returnsEmptyList() {
        UUID userId = UUID.randomUUID();

        when(securityUtils.getCurrentUserId()).thenReturn(userId);
        when(repo.findByUserId(userId)).thenReturn(List.of());

        List<TrainingPlanListDTO> result = service.getAll();

        assertTrue(result.isEmpty());
        verify(repo).findByUserId(userId);
        verifyNoInteractions(mRepo);
    }

    @Test
    void getAll_whenUserHasPlan_returnsDto() {
        UUID userId = UUID.randomUUID();
        UUID marathonId = UUID.randomUUID();

        TrainingPlan plan = createPlan(userId, marathonId, "ONGOING");

        Marathon marathon = new Marathon();
        marathon.setId(marathonId);
        marathon.setName("Eindhoven Marathon");

        when(securityUtils.getCurrentUserId()).thenReturn(userId);
        when(repo.findByUserId(userId)).thenReturn(List.of(plan));
        when(mRepo.findById(marathonId)).thenReturn(Optional.of(marathon));

        List<TrainingPlanListDTO> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals(plan.getId().toString(), result.get(0).getId());
        assertEquals("Eindhoven Marathon", result.get(0).getMarathonName());
        assertEquals("2026-01-01", result.get(0).getStartDate());
        assertEquals("2026-04-01", result.get(0).getEndDate());
        assertEquals("ONGOING", result.get(0).getStatus());
    }

    @Test
    void getAll_usesCurrentUserIdToFindPlans() {
        UUID userId = UUID.randomUUID();

        when(securityUtils.getCurrentUserId()).thenReturn(userId);
        when(repo.findByUserId(userId)).thenReturn(List.of());

        service.getAll();

        verify(repo).findByUserId(userId);
        verify(repo, never()).findAll();
    }

    @Test
    void getAll_whenMarathonDoesNotExist_throwsEntityNotFoundException() {
        UUID userId = UUID.randomUUID();
        UUID marathonId = UUID.randomUUID();

        TrainingPlan plan = createPlan(userId, marathonId, "ONGOING");

        when(securityUtils.getCurrentUserId()).thenReturn(userId);
        when(repo.findByUserId(userId)).thenReturn(List.of(plan));
        when(mRepo.findById(marathonId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getAll());
    }

    @Test
    void getAll_whenUserHasMultiplePlans_returnsMultipleDtos() {
        UUID userId = UUID.randomUUID();

        UUID marathonId1 = UUID.randomUUID();
        UUID marathonId2 = UUID.randomUUID();

        TrainingPlan plan1 = createPlan(userId, marathonId1, "ONGOING");
        TrainingPlan plan2 = createPlan(userId, marathonId2, "FINISHED");

        Marathon marathon1 = new Marathon();
        marathon1.setId(marathonId1);
        marathon1.setName("Eindhoven Marathon");

        Marathon marathon2 = new Marathon();
        marathon2.setId(marathonId2);
        marathon2.setName("Rotterdam Marathon");

        when(securityUtils.getCurrentUserId()).thenReturn(userId);
        when(repo.findByUserId(userId)).thenReturn(List.of(plan1, plan2));
        when(mRepo.findById(marathonId1)).thenReturn(Optional.of(marathon1));
        when(mRepo.findById(marathonId2)).thenReturn(Optional.of(marathon2));

        List<TrainingPlanListDTO> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("Eindhoven Marathon", result.get(0).getMarathonName());
        assertEquals("Rotterdam Marathon", result.get(1).getMarathonName());
        assertEquals("ONGOING", result.get(0).getStatus());
        assertEquals("FINISHED", result.get(1).getStatus());
    }

    private TrainingPlan createPlan(UUID userId, UUID marathonId, String status) {
        TrainingPlan plan = new TrainingPlan();
        plan.setId(UUID.randomUUID());
        plan.setUserId(userId);
        plan.setMarathonId(marathonId);
        plan.setStartDate(LocalDate.of(2026, 1, 1));
        plan.setEndDate(LocalDate.of(2026, 4, 1));
        plan.setStatus(status);
        return plan;
    }
    @Test
void createPlan_whenInputIsValid_savesPlanAndGeneratesItems() {
    UUID userId = UUID.randomUUID();
    UUID marathonId = UUID.randomUUID();

    TrainingPlanInputForm input = createInput(marathonId);

    Marathon marathon = new Marathon();
    marathon.setId(marathonId);
    marathon.setDate(LocalDate.of(2026, 10, 11));

    when(securityUtils.getCurrentUserId()).thenReturn(userId);
    when(mRepo.findById(marathonId)).thenReturn(Optional.of(marathon));

    service.createPlan(input);

    verify(repo).save(argThat(plan ->
            plan.getUserId().equals(userId)
                    && plan.getMarathonId().equals(marathonId)
                    && plan.getStatus().equals("ONGOING")
                    && plan.getStartDate().equals(LocalDate.now())
                    && plan.getEndDate().equals(LocalDate.of(2026, 10, 11))
    ));

    verify(itemService).generateItems(any(TrainingPlan.class), eq(input));
}
@Test
void createPlan_whenMarathonDoesNotExist_throwsEntityNotFoundException() {
    UUID userId = UUID.randomUUID();
    UUID marathonId = UUID.randomUUID();

    TrainingPlanInputForm input = createInput(marathonId);

    when(securityUtils.getCurrentUserId()).thenReturn(userId);
    when(mRepo.findById(marathonId)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.createPlan(input));

    verify(repo, never()).save(any());
    verify(itemService, never()).generateItems(any(), any());
}
@Test
void createPlan_whenRepoSaveFails_doesNotGenerateItems() {
    UUID userId = UUID.randomUUID();
    UUID marathonId = UUID.randomUUID();

    TrainingPlanInputForm input = createInput(marathonId);

    Marathon marathon = new Marathon();
    marathon.setId(marathonId);
    marathon.setDate(LocalDate.of(2026, 10, 11));

    when(securityUtils.getCurrentUserId()).thenReturn(userId);
    when(mRepo.findById(marathonId)).thenReturn(Optional.of(marathon));
    when(repo.save(any(TrainingPlan.class)))
            .thenThrow(new RuntimeException("Database error"));

    assertThrows(RuntimeException.class, () -> service.createPlan(input));

    verify(itemService, never()).generateItems(any(), any());
}
@Test
void createPlan_whenItemGenerationFails_throwsException() {
    UUID userId = UUID.randomUUID();
    UUID marathonId = UUID.randomUUID();

    TrainingPlanInputForm input = createInput(marathonId);

    Marathon marathon = new Marathon();
    marathon.setId(marathonId);
    marathon.setDate(LocalDate.of(2026, 10, 11));

    when(securityUtils.getCurrentUserId()).thenReturn(userId);
    when(mRepo.findById(marathonId)).thenReturn(Optional.of(marathon));
    doThrow(new RuntimeException("Item generation failed"))
            .when(itemService).generateItems(any(TrainingPlan.class), eq(input));

    assertThrows(RuntimeException.class, () -> service.createPlan(input));

    verify(repo).save(any(TrainingPlan.class));
}

private TrainingPlanInputForm createInput(UUID marathonId) {
    TrainingPlanInputForm input = new TrainingPlanInputForm();
    input.setFitnessLevel(3);
    input.setMarathonId(marathonId);
    input.setTargetTime(LocalTime.of(4, 0));
    input.setTrainingsPerWeek(4);
    input.setAvailableDays(List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY));
    return input;
}
}