package net.noobsters.core.paper.Practice;

import java.util.UUID;

import org.bukkit.Location;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Arena{
    private String inUseBy = "none"; //Match ID
    private String mapID = UUID.randomUUID().toString();
    private String mapType;
    private Location pos1;
    private Location pos2;
    private Location spawn1;
    private Location spawn2;
    private Location center;


    public boolean notInUse(){
        if(inUseBy == "none") return true;
        return false;
    }
}
