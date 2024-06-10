package ci.luckyman.uobackend.repository;

import ci.luckyman.uobackend.entities.Opinion;
import ci.luckyman.uobackend.enums.OpinionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion> findByType(OpinionType type );
}
