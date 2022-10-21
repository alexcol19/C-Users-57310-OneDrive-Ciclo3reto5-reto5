package com.example.reto5.Repository;

import com.example.reto5.Repository.CrudRepository.ReservationCrudRepository;
import com.example.reto5.entities.Client;
import com.example.reto5.entities.DTOs.CountClient;
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

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationsCrudRepository.findAll();
    }
    public Optional<Reservation> getReservationId(int id){
        return reservationsCrudRepository.findById(id);
    }
    public Reservation save(Reservation reservation){
        return reservationsCrudRepository.save(reservation);
    }
    public void delete(Reservation reservation){
        reservationsCrudRepository.delete(reservation);
    }



    //reto5
    public List<Reservation> getReservationsBetweenDates(Date a, Date b) {
        return reservationsCrudRepository.findAllByStartDateAfterAndDevolutionDateBefore(a, b);
    }

    public List<Reservation> getReservationsByStatus(String status) {
        return reservationsCrudRepository.findAllByStatus(status);
    }

    public List<CountClient> getTopClients() {
        List<CountClient> respuesta = new ArrayList<>();
        List<Object[]> reporte = reservationsCrudRepository.countTotalReservationByClient();
        for(int i = 0; i<reporte.size(); i++){
            respuesta.add(new CountClient( (Long) reporte.get(i)[1], (Client) reporte.get(i)[0]));

        }
        return respuesta;

    }

}
