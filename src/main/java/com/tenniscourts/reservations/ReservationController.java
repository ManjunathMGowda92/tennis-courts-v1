package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;


    @PostMapping("/reservations")
    @ApiOperation(
            value = "Book Reservation",
            notes = "API to book the Reservation",
            httpMethod = "POST"
    )
    public ResponseEntity<Void> bookReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @GetMapping("/reservations/{id}")
    @ApiOperation(
            value = "Find Reservation",
            notes = "API used to find the reservations by reservationId",
            produces = "APPLICATION_JSON_VALUE",
            httpMethod = "GET"
    )
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable("id") Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @PutMapping("/reservations/cancel/{id}")
    @ApiOperation(
            value = "Cancel Reservation",
            notes = "API used to cancel the reservation by reservationId",
            httpMethod = "PUT"
    )
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable("id") Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @PutMapping("/reservations/{id}/reschedule/{rescheduleId}")
    @ApiOperation(
            value = "Reschedule the Reservation",
            notes = "API used to reschedule the reservation",
            httpMethod = "PUT"
    )
    public ResponseEntity<ReservationDTO> rescheduleReservation(@PathVariable("id") Long reservationId,
                                                                @PathVariable("rescheduleId") Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
