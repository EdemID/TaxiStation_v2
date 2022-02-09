package elp.max.e.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "dispatcher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dispatcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dayoff;
    @Column(name = "start_lunch")
    private LocalTime startLunch;
    @Column(name = "end_lunch")
    private LocalTime endLunch;
    @Column(name = "workstatus")
    private boolean workStatus;
}
