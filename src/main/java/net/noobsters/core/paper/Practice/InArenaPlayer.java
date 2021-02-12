package net.noobsters.core.paper.Practice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class InArenaPlayer {
    private ArenaPlayer arenaPlayer;
    private boolean isAlive = false;
    private Integer teamID = 0;
    // team ID = 0 FFA
    // team ID = equipo al que pertenece
    private String matchID;
    
}
