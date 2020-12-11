package net.noobsters.core.paper.Arena;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class InArenaPlayer {
    private ArenaPlayer player;
    private boolean isAlive = false;
    private String teamID;
    private Match match;

    
}
