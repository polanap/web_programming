package table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@NoArgsConstructor
public class TableRow implements Serializable {
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private double R;  
    @Getter
    @Setter
    private String resultData;
    @Getter
    @Setter
    private String currentTime;
    @Getter
    @Setter
    private double executionTime;      
}