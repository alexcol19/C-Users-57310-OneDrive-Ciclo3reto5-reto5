package com.example.reto5.Services;

import com.example.reto5.Repository.CostumeRepository;
import com.example.reto5.entities.Costume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumeService {
    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll(){return (List<Costume>) costumeRepository.getAll();}

    public Optional<Costume> getCostume(int id){
        return costumeRepository.getCostumeId(id);
    }
    public Costume save(Costume costume){
        if(costume.getId()==null){
            return costumeRepository.save(costume);
        }else{
            Optional<Costume> paux= costumeRepository.getCostumeId(costume.getId());
            if(paux.isEmpty()){
                return costumeRepository.save(costume);
            }else
                return costume;
        }
    }
    public Costume update(Costume costume) {
        if (costume.getId() != null) {
            Optional<Costume> e = costumeRepository.getCostumeId(costume.getId()); //ojo//
            if (!e.isEmpty()) {
                if (costume.getName() != null) {
                    e.get().setName(costume.getName());
                }
                if (costume.getBrand() != null) {
                    e.get().setBrand(costume.getBrand());
                }
                if (costume.getYears() != null) {
                    e.get().setYear(costume.getYears());
                }
                if (costume.getDescription() != null) {
                    e.get().setDescription(costume.getDescription());
                }
                if (costume.getCategory() != null) {
                    e.get().setCategory(costume.getCategory());
                }
                if (costume.getReservations() != null) {
                    e.get().setReservations(costume.getReservations());
                }
                if (costume.getMessages() != null) {
                    e.get().setMessages(costume.getMessages());
                }
                costumeRepository.save(e.get()); //ojo//
                return e.get();
            }else{
                return costume;
            }
        }else{
            return costume;
        }
    }
    public boolean deleteCostume(int id){
        Boolean r = getCostume(id).map(costume -> {
            costumeRepository.delete(costume);
            return true;
        }).orElse(false);
        return r;

    }


}
