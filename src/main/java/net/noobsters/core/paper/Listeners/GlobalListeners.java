package net.noobsters.core.paper.Listeners;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.noobsters.core.paper.Valhalla;
import net.noobsters.core.paper.Practice.ArenaPlayer;

public class GlobalListeners implements Listener{

    Valhalla instance;
    Random random = new Random();

    GlobalListeners(Valhalla instance){
        this.instance = instance;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        var uuid = e.getPlayer().getUniqueId().toString();
        var name = e.getPlayer().getName().toString();
        ArenaPlayer arenaPlayer = ArenaPlayer.of(name, uuid, null, "none", "none");
        instance.getArenaManager().getArenaPlayers().put(uuid, arenaPlayer);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        var uuid = e.getPlayer().getUniqueId().toString();
        var arenaPlayer = instance.getArenaManager().getArenaPlayers().get(uuid);
        var arenaManager = instance.getArenaManager();

        //REMOVE PLAYER FROM EVERYWHERE ON QUIT

        //removed from the queue if is in
        if(arenaPlayer.isInQueue()){
            arenaPlayer.setQueue("none");
            arenaManager.getQueue().remove(uuid);
        }
        
        if(arenaPlayer.isInMatch()){
            var teamID = arenaPlayer.getInArenaPlayer().getTeamID();
            var match = arenaManager.getMatches().get(arenaPlayer.getMatch());
            match.getMatchPlayers().get(teamID).remove(arenaPlayer.getInArenaPlayer());
            arenaPlayer.setMatch("none");
            arenaPlayer.setInArenaPlayer(null);
            //msg leaving match
        }

        //removed from arena players
        arenaManager.getArenaPlayers().remove(uuid);
    
    }


}