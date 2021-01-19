package net.noobsters.core.paper.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.noobsters.core.paper.Valhalla;
import net.noobsters.core.paper.Practice.ArenaPlayer;

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
        ArenaPlayer arenaPlayer = ArenaPlayer.of(uuid, "none", "none");
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

        }
        //removed from arena players
        instance.getArenaManager().getArenaPlayers().remove(uuid);
    
    }

}