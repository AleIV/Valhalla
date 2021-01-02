package net.noobsters.core.paper.Arena;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ArenaPlayer {
    private String arenaplayerUUID;
    private boolean inMatch = false;
    private boolean inQueue = false;
    private String Queue = "none";
    
}
