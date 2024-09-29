package web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
public class TableHistoryBean implements Serializable {
    @Getter
    @Setter
    private List<String> history = new ArrayList<>();
}