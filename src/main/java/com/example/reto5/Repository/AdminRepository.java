package com.example.reto5.Repository;

import com.example.reto5.Repository.CrudRepository.AdminCrudRepository;
import com.example.reto5.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {
    @Autowired
    private AdminCrudRepository adminCrudRepository;

    public List<Admin> getAdmin(){
        return (List<Admin>) adminCrudRepository.findAll();
    }
    public Optional<Admin> getAdminId(int id){
        return adminCrudRepository.findById(id);
    }
    public Admin saveAdmin(Admin admin){
        return adminCrudRepository.save(admin);
    }
    public void deleteAdmin(Admin admin){
        adminCrudRepository.delete(admin);
    }

    public List<Admin> getAll() {return (List<Admin>) adminCrudRepository.findAll();
    }


}
