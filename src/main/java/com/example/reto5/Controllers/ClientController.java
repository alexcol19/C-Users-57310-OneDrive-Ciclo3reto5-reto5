package com.example.reto5.Controllers;

import com.example.reto5.Services.ClientService;
import com.example.reto5.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class ClientController {

        @Autowired
        private ClientService clientService;

        @GetMapping("/all")
        public List<Client> getAll(){
            return clientService.getAll();
        }

        @GetMapping("/{id}")
        public Optional<Client> getClient(@PathVariable("id") int id){
            return clientService.getIdClient(id);
        }

        @PostMapping("/save")
        @ResponseStatus(HttpStatus.CREATED)
        public Client saveClient(@RequestBody Client client){

            return clientService.saveClient(client);
        }

        @PutMapping("/update")
        @ResponseStatus(HttpStatus.CREATED)
        public Client update(@RequestBody Client client) {
            return clientService.update(client);

        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public boolean delete(@PathVariable("id") int id){
            return clientService.deleteClient(id);
        }

}

