package net.noobsters.core.paper.Practice;

import java.util.UUID;

import org.bukkit.Bukkit;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.noobsters.core.paper.Valhalla;

@Data
@AllArgsConstructor(staticName = "of")
public class InArenaPlayer {
    private ArenaPlayer arenaPlayer;
    private boolean isAlive = false;
    private Integer teamID = 0;
    // team ID = equipo al que pertenece
    private String matchID;
    
    public void wearKit(String kitName){
        var player = Bukkit.getPlayer(UUID.fromString(arenaPlayer.getArenaplayerUUID()));
        var kit = Valhalla.getInstance().getArenaManager().getKits().get(kitName);

        player.getInventory().setContents(kit.getKit().clone());
    }

}
