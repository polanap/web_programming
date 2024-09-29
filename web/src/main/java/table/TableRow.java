package table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

import java.io.Serializable;

@ToString
@NoArgsConstructor
public class TableRow implements Serializable {
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private float y;
    @Getter
    @Setter
    private int R;  
    @Getter
    @Setter
    private String result;
    @Getter
    @Setter
    private LocalDateTime currentTime;
    @Getter
    @Setter
    private float executionTime;      
}