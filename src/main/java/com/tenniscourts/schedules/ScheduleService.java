package com.tenniscourts.schedules;

import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleMapper scheduleMapper;

    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
        //TODO: implement addSchedule
        return null;
    }

    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        //TODO: implement
        List<Schedule> schedules = scheduleRepository.findByStartDateTimeAndEndDateTime(startDate, endDate);
        if (schedules == null || schedules.size() == 0) {
            throw new BusinessException("No Schedules found for the given period");
        }

        List<ScheduleDTO> scheduleDTOList = schedules.stream()
                                                    .map(scheduleMapper::map)
                                                    .collect(Collectors.toList());
        return scheduleDTOList;
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
        //TODO: implement
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            ScheduleDTO scheduleDTO = scheduleMapper.map(schedule);
            return scheduleDTO;
        }

        throw new EntityNotFoundException("Schedule not found for the scheduleId: "+scheduleId);
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
}
