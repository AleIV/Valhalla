package net.noobsters.core.paper.Arena;

import com.google.common.util.concurrent.Service.Listener;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Arena extends Listener{
    private boolean inUse = false;
    private String mapID;
    private String mapType;
    private Location pos1;
    private Location pos2;
    private Location spawn1;
    private Location spawn2;
    private Location center;

    @EventHandler
    public void blockBreak(BlockBreakEvent e){
        if(e.getPlayer().hasPermission("lobby.edit")) return;
        var block = e.getBlock().getType().toString();
        switch(block){
            case "COBBLESTONE":
            case "OAK_PLANKS":
            return;
        }
        e.setCancelled(true);
    }

    
}
