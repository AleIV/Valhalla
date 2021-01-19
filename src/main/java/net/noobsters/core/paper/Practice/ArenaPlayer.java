package net.noobsters.core.paper.Practice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ArenaPlayer {
    private String arenaplayerUUID;
    private String match = "none";
    private String queue = "none";


    public boolean isInMatch(){
        if(match != "none") return true;
        return false;
    }
    
    public boolean isInQueue(){
        if(queue != "none") return true;
        return false;
    }

}
