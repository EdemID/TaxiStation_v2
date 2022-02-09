package elp.max.e.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_number")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String client;
    private String dispatcher;
    private String driver;
    private String car;
}
