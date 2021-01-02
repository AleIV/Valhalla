package net.noobsters.core.paper.Arena;

import org.bukkit.Location;
import org.bukkit.event.Listener;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Arena implements Listener{
    private boolean inUse = false;
    private String mapID;
    private String mapType;
    private Location pos1;
    private Location pos2;
    private Location spawn1;
    private Location spawn2;
    private Location center;

    //changes blocks

    /*
    for (i=0;i<20;i++){
        for (n=0;n<20;n++){
            for (x=0;x<20;x++){
                if matriz[x][n][i] != matriz2[x][n][i]
            }
        }

    }*/
}
