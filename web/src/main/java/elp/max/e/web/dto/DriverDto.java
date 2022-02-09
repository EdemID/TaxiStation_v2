package elp.max.e.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {

    private Long id;
    private String name;
    private String dayoff;
    private String car;
    private boolean workStatus;
    private boolean busy;
}
