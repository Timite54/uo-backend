package ci.luckyman.uobackend.service;

import ci.luckyman.uobackend.entities.Client;
import ci.luckyman.uobackend.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public  Client existingClient(Client email){
        Client exist = clientRepository.findOneByEmail(email.getEmail());
        return exist;
    }
    public ResponseEntity<String> creer(Client client) {
//        existingClient(client);
//        Client existing = clientRepository.findOneByEmail(client.getEmail());
        if(existingClient(client) == null) {
            clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body("Parfait! vous êtes enregistre");
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cet email exist déjà, veuillez founir un nouvel email");
        }

//       Client existing = clientRepository.findByEmail(client.getEmail());
//       if (existing == null) {
//           clientRepository.save(client);
//       }
    }

    public List<Client> getClients() {
       return clientRepository.findAll();
    }

    public  Client search(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
//        if (optionalClient.isPresent()) {
//            return optionalClient.get();
//        }else {
//            return null;
//        }
    }

    public Client readOrCreate(Client newClient) {
        Client existingInBd = clientRepository.findOneByEmail(newClient.getEmail());
        if(existingInBd == null) {
           existingInBd = clientRepository.save(newClient);
        }
        return existingInBd;
    }

    public Client deleteClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.deleteById(id);
        }
        return client.get();
    }

    public ResponseEntity<String> updateClient(Long id, Client client) {
        Client existingInBd = search(id);
        existingClient(client);
        if (existingClient(client) == null) {
            if(existingInBd.getId().equals(client.getId())) {
//                existingInBd.setEmail(client.getEmail());
                existingInBd.setTelephone(client.getTelephone());
                clientRepository.save(existingInBd);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client updated");
            }else {
                return  ResponseEntity.status(HttpStatus.CONFLICT).body("Ce client exist");
            }
        } else return ResponseEntity.status(HttpStatus.CONFLICT).body("impossible");
    }
}