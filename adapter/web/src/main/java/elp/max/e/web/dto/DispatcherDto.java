package elp.max.e.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispatcherDto {

    private Long id;
    private String name;
    private String dayoff;
    private String startLunch;
    private String endLunch;
    private boolean workStatus;
}
