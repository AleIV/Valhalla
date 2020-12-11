package net.noobsters.core.paper.Arena;

import org.bukkit.Location;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Arena {
    private boolean inUse = false;
    private String mapID;
    private String mapType;
    private Location pos1;
    private Location pos2;
    private Location spawn1;
    private Location spawn2;
    private Location center;


    
}
