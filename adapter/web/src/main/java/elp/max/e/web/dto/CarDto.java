package elp.max.e.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long id;
    private String numberCar;
    private Integer resource;
    private boolean busy;
}
