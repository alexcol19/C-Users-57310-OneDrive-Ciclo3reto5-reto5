package com.example.reto5.Repository;

import com.example.reto5.Repository.CrudRepository.ReservationCrudRepository;
import com.example.reto5.entities.Client;
import com.example.reto5.entities.DTOs.TotalAndClient;
import com.example.reto5.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationsCrudRepository;

    public List<Reservation> getReservations(){
        return (List<Reservation>) reservationsCrudRepository.findAll();
    }
    public Optional<Reservation> getReservationId(int id){
        return reservationsCrudRepository.findById(id);
    }
    public Reservation saveReservation(Reservation reservation){
        return reservationsCrudRepository.save(reservation);
    }
    public void deleteReservation(Reservation reservation){
        reservationsCrudRepository.delete(reservation);
    }



    //reto5
    public List<Reservation> getReservationsBetweenDates(Date a, Date b) {
        return reservationsCrudRepository.findAllByStartDateAfterAndDevolutionDateBefore(a, b);
    }

    public List<Reservation> getReservationsByStatus(String status) {
        return reservationsCrudRepository.findAllByStatus(status);
    }

    public List<TotalAndClient> getTopClients() {
        List<TotalAndClient> respuesta = new ArrayList<>();
        List<Object[]> reporte = reservationsCrudRepository.getTotalReservationByClient();
        for(int i=0; i<reporte.size(); i++){
            respuesta.add(new TotalAndClient( (Long) reporte.get(i)[1], (Client) reporte.get(i)[0]));

        }
        return respuesta;

    }
}
