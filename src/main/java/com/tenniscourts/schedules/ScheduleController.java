package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@RestController
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    //TODO: implement rest and swagger
    @PostMapping("/scheduleCourt")
    @ApiOperation(
            value = "Schedule Tennis Court",
            notes = "API to Schedule the Tennis Court",
            httpMethod = "POST",
            consumes = "APPLICATION_JSON_VALUE"
    )
    public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    //TODO: implement rest and swagger
    @GetMapping("/getSchedules")
    @ApiOperation(
            value = "Get Schedule Dates",
            notes = "API to get the schedules by date",
            httpMethod = "GET"
    )
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@RequestParam(name = "startDate", required = true) LocalDate startDate,
                                                                  @RequestParam(name = "endDate", required = true) LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    //TODO: implement rest and swagger
    @GetMapping("/getSchedules/{id}")
    @ApiOperation(
            value = "Get Schedule By Id",
            notes = "API to get the schedule by id",
            httpMethod = "GET"
    )
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable("id") Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
