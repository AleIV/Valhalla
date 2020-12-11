package net.noobsters.core.paper.Arena;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Match {
    private String matchID;
    private Integer timer;
    private Integer rounds;
    private List<InArenaPlayer> players;
    private Arena arena;
    private Kit kit;


}
