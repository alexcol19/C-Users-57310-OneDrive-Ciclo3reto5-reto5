package com.example.reto5.Repository;

import com.example.reto5.Repository.CrudRepository.CostumeCrudRepository;
import com.example.reto5.entities.Costume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CostumeRepository {
    @Autowired
    private CostumeCrudRepository costumeCrudRepository;


    public List<Costume> getCostumes(){
        return (List<Costume>) costumeCrudRepository.findAll();
    }
    public Optional<Costume> getCostumeId(int id){
        return costumeCrudRepository.findById(id);
    }
    public Costume saveCostume(Costume costume){
        return costumeCrudRepository.save(costume);
    }
    public void deleteCostume(Costume costume){
        costumeCrudRepository.delete(costume);
    }
}
