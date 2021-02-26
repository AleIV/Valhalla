package net.noobsters.core.paper.Listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.noobsters.core.paper.Valhalla;

public class MatchListeners implements Listener{

    Valhalla instance;
    
    MatchListeners(Valhalla instance){
        this.instance = instance;
    }
    

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        
        var playerUUID = e.getEntity().getUniqueId().toString();
        var arenaManager = instance.getArenaManager();
        var arenaPlayer = arenaManager.getArenaPlayers().get(playerUUID);

        if(arenaPlayer.isInMatch()){
            //set player dead when died in match
            arenaPlayer.getInArenaPlayer().setAlive(false);
        }

        //check if someone has to win
        

    }

    public void winnerAnimation(String playerUUID){
        var player = Bukkit.getPlayer(UUID.fromString(playerUUID));
        Bukkit.broadcastMessage(player.getName().toString() + " WINS");
    }

}