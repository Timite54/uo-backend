package ci.luckyman.uobackend.repository;

import ci.luckyman.uobackend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
//    Optional<Client> findByEmail(String email);

    Client findOneByEmail(String email);
}
