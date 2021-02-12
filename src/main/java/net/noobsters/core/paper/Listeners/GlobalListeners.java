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
        ArenaPlayer arenaPlayer = ArenaPlayer.of(name, uuid, "none", "none");
        instance.getArenaManager().getArenaPlayers().put(uuid, arenaPlayer);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        var uuid = e.getPlayer().getUniqueId().toString();
        var arenaPlayer = instance.getArenaManager().getArenaPlayers().get(uuid);

        //removed from the queue if is in
        if(instance.getArenaManager().getQueue().containsKey(uuid)){
            arenaPlayer.setQueue("none");
            instance.getArenaManager().getQueue().remove(uuid);

        }else if(instance.getArenaManager().getMatches().containsKey(uuid)){

        }
        //removed from arena players
        instance.getArenaManager().getArenaPlayers().remove(uuid);
    
    }


}