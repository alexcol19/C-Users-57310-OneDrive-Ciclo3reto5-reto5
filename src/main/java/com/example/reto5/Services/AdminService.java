package com.example.reto5.Services;

import com.example.reto5.Repository.AdminRepository;
import com.example.reto5.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAdmin(int id){
        return adminRepository.getAdmin();
    }
    public Optional<Admin> getAdminId(int id){
        return adminRepository.getAdminId(id);
    }
    public Admin saveAdmin(Admin admin){
        if(admin.getIdAdmin()==null){
            return adminRepository.saveAdmin(admin);
        }else{
            Optional<Admin> paux= adminRepository.getAdminId(admin.getIdAdmin());
            if(paux.isEmpty()){
                return adminRepository.saveAdmin(admin);
            }else
                return admin;
        }
    }

    public Admin update(Admin admin){
        if(admin.getIdAdmin() != null){
            Optional<Admin> paux = adminRepository.getAdminId(admin.getIdAdmin());
            if(!paux.isEmpty()){
                if (admin.getPassword() != null){
                    paux.get().setPassword(admin.getPassword());
                }
                if (admin.getName() != null){
                    paux.get().setName(admin.getName());
                }
                if (admin.getEmail() != null){
                    paux.get().setEmail(admin.getEmail());
                }
                return adminRepository.saveAdmin(paux.get());
            }
        }
        return admin;
    }

    public boolean deleteAdmin (int id){
        Boolean r = getAdminId(id).map(admin->{
            adminRepository.deleteAdmin(admin);
            return true;
        }).orElse(false);

        return r;
    }

    public List<Admin> getAll() {return (List<Admin>) adminRepository.getAll();
    }






}
