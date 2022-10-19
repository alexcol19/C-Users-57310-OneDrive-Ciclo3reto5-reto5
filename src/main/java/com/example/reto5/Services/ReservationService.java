package com.example.reto5.Services;

import com.example.reto5.Repository.ReservationRepository;
import com.example.reto5.entities.DTOs.CompletedAndCancelled;
import com.example.reto5.entities.DTOs.TotalAndClient;
import com.example.reto5.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservations(){
        return reservationRepository.getReservations();
    }
    public Optional<Reservation> getReservationId(int id){
        return reservationRepository.getReservationId(id);
    }
    public Reservation saveReservation(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return reservationRepository.saveReservation(reservation);
        }else{
            Optional<Reservation> paux= reservationRepository.getReservationId(reservation.getIdReservation());
            if(paux.isEmpty()){
                return reservationRepository.saveReservation(reservation);
            }else
                return reservation;
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation() != null){
            Optional<Reservation> paux = reservationRepository.getReservationId(reservation.getIdReservation());
            if(!paux.isEmpty()){
                if(reservation.getIdReservation() != null){
                    paux.get().setIdReservation(reservation.getIdReservation());
                }
                if(reservation.getClient() != null){
                    paux.get().setClient(reservation.getClient());
                }
                if(reservation.getCostume() != null){
                    paux.get().setCostume(reservation.getCostume());
                }
                if(reservation.getDevolutionDate() != null){
                    paux.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getScore() != null){
                    paux.get().setScore(reservation.getScore());
                }
                if(reservation.getStartDate() != null){
                    paux.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getStatus() != null){
                    paux.get().setStatus(reservation.getStatus());
                }
                if (reservation.getEndate() != null){
                    paux.get().setEndDate(reservation.getEndate());
                }
                return reservationRepository.saveReservation(paux.get());
            }
        }
        return reservation;

    }
    public boolean deleteReservation(int id){
        Boolean r = getReservationId(id).map(reservations ->{
            reservationRepository.deleteReservation(reservations);
            return true;
        }).orElse(false);

        return r;
    }

    //Reto5
    public List<Reservation> getReservationsBetweenDatesReport(String dateA, String dateB){
        SimpleDateFormat parser = new SimpleDateFormat("yyy -MM-dd");
        Date a = new Date();
        Date b = new Date();
        try{
            a = parser.parse(dateA);
            b = parser.parse(dateB);
        }catch (ParseException exception){
            exception.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.getReservationsBetweenDates(a, b);
        }else {
            return new ArrayList<>();
        }

    }
    public CompletedAndCancelled getReservationStatusReport(){
        List<Reservation> completed = reservationRepository.getReservationsByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationsByStatus("cancelled");

        int cantidadCompletas = completed.size();
        int cantidadCanceladas = cancelled.size();

        return new CompletedAndCancelled( (long) cantidadCompletas, (long) cantidadCanceladas);

    }
    public List<TotalAndClient> getTopClientsReport(){
        return reservationRepository.getTopClients();
    }
}
