package com.example.reto5.Controllers;

import com.example.reto5.Services.ReservationService;
import com.example.reto5.entities.DTOs.CompletedAndCancelled;
import com.example.reto5.entities.DTOs.CountClient;
import com.example.reto5.entities.DTOs.CountStatus;
import com.example.reto5.entities.DTOs.TotalAndClient;
import com.example.reto5.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class ReservationController {

    @Autowired
    private ReservationService reservationsService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationsService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationId(@PathVariable("id") int id){
        return reservationsService.getReservationId(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveReservation(@RequestBody Reservation reservation){
        return reservationsService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation){
        return reservationsService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return reservationsService.deleteReservation(id);
    }


    //reto5

    @GetMapping("/report-clients")
    public List<CountClient> getTopClientsReport(){
        return reservationsService.getTopClients();

    }
    @GetMapping("/report-dates/{date1}/{date2}")
    public List<Reservation> getReservationsBetweenDatesReport(@PathVariable("date1") String date1,
                                                                @PathVariable("date2") String date2){
        return reservationsService.getReservationsBetweenDatesReport(date1, date2);

    }
    @GetMapping( "/report-status")
    public CountStatus getReservationsStatusReport(){
        return reservationsService.getReservationsStatus();
    }
    /*@GetMapping("/report-status")
    public CompletedAndCancelled getReservationStatusreport(){
        return reservationsService.getReservationsStatusReport();
    }*/


}
