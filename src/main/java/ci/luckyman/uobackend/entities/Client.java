package ci.luckyman.uobackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String telephone;
    @CurrentTimestamp
    private Timestamp creation;
    @Column(name = "mise_a_jour")
    private Timestamp miseAJour;

}
