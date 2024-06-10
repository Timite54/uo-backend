package ci.luckyman.uobackend.service;

import ci.luckyman.uobackend.entities.Client;
import ci.luckyman.uobackend.entities.Opinion;

import ci.luckyman.uobackend.enums.OpinionType;
import ci.luckyman.uobackend.repository.OpinionRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
 @AllArgsConstructor
public class OpinionService {
    private OpinionRepository opinionRepository;
    private ClientService clientService;


    public void creer(Opinion opinion) {
        Optional<Client> client = Optional.ofNullable(clientService.readOrCreate(opinion.getClient()));
        opinion.setClient(client.get());
        if (opinion.getText().contains("pas")) {
            opinion.setType(OpinionType.NEGATIVE);
        }else {
            opinion.setType(OpinionType.POSITIVE);
        }
        opinionRepository.save(opinion);
    }

    public List<Opinion> allOpinion(OpinionType type) {
        if (type == null){
            return  opinionRepository.findAll();
        }else {
            return opinionRepository.findByType(type);
        }
    }

    public void deleteOpinion(Long id) {
        opinionRepository.deleteById(id);
    }
}
