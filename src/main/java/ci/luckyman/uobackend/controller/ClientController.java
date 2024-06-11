package ci.luckyman.uobackend.controller;

import ci.luckyman.uobackend.entities.Client;
import ci.luckyman.uobackend.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "client")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> creer(@RequestBody Client client) {
        return clientService.creer(client);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Client> search() {
        return clientService.getClients();
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public Client getClientById(@PathVariable Long id) {
        return clientService.search(id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable Long id) {
        Client deletedClient = clientService.deleteClientById(id);
        if (deletedClient == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aucun client n'existe pour cet ID");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client supprime avec succes");
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }
}
