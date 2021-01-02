package net.noobsters.core.paper.Listeners;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.noobsters.core.paper.Valhalla;
import net.noobsters.core.paper.Arena.ArenaPlayer;

public class GlobalListeners implements Listener{

    Valhalla instance;
    
    GlobalListeners(Valhalla instance){
        this.instance = instance;
    }
    

    @EventHandler
    public void blockBreak(BlockBreakEvent e){
        var player = e.getPlayer();
        if(player.getWorld().getName().toString() == "Valhalla" || player.getGameMode() == GameMode.CREATIVE && player.hasPermission("lobby.edit")) return;
        var block = e.getBlock().getType().toString();
        switch(block){
            case "COBBLESTONE":
            case "OAK_PLANKS":
            // o blocks de la los change locs
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        var uuid = e.getPlayer().getUniqueId().toString();
        ArenaPlayer arenaPlayer = ArenaPlayer.of(uuid, false, false, "none");
        instance.getArenaManager().getArenaPlayers().put(uuid, arenaPlayer);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        var uuid = e.getPlayer().getUniqueId().toString();
        var arenaPlayer = instance.getArenaManager().getArenaPlayers().get(uuid);

        //removed from the queue is in
        if(arenaPlayer.isInQueue()){
            var queue = instance.getArenaManager().getQueue().get(arenaPlayer.getQueue());
            arenaPlayer.setInQueue(false);
            arenaPlayer.setQueue("none");
            queue.remove(arenaPlayer);

        }
        //removed from arena players
        instance.getArenaManager().getArenaPlayers().remove(uuid);
    
    }

}