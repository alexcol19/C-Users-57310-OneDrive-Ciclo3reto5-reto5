package com.example.reto5.Repository;

import com.example.reto5.Repository.CrudRepository.ClientCrudRepository;
import com.example.reto5.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    @Autowired

    private ClientCrudRepository clientCrudRepository;

    public List<Client> getAll(){
        return (List<Client>) clientCrudRepository.findAll();
    }
    public Optional<Client> getClientId(Integer id){
        return clientCrudRepository.findById(id);
    }
    public Client saveClient(Client client){
        return clientCrudRepository.save(client);
    }
    public void deleteClient(Client client){
        clientCrudRepository.delete(client);
    }
}
