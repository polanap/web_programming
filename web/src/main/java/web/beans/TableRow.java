package web.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name="attempts")
public class TableRow implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private int id;

    @NotNull
    @Column(name="x", nullable=false)
    private double x = 1;

    @NotNull
    @Column(name="y", nullable=false)   
    private double y = 1;

    @NotNull
    @Column(name="r", nullable=false)
    private double r = 1;

    @NotNull
    @Column(name="result", nullable=false)
    private String resultData;

    @NotNull
    @Column(name="currentTime", nullable=false)
    private LocalDateTime currentTime;

    @NotNull
    @Column(name="executionTime", nullable=false)
    private double executionTime;  

}