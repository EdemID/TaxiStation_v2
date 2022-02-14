package elp.max.e.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dayoff;
    private String car;
    @Column(name = "workstatus", columnDefinition = "boolean DEFAULT true")
    private boolean workStatus;
    @Column(columnDefinition = "boolean DEFAULT false")
    private boolean busy;
}
