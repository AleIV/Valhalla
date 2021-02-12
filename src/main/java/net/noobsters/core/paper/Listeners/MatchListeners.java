package net.noobsters.core.paper.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.noobsters.core.paper.Valhalla;
import net.noobsters.core.paper.Practice.Match;

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

        if(!arenaPlayer.isInMatch()) return;

        var match = arenaManager.getMatches().get(arenaPlayer.getMatch());

        //set player dead when died in match
        match.getMatchPlayers().get(playerUUID).setAlive(false);

        //check if someone has to win
        

    }

    public boolean checkEndOfMatch(Match match){
        //FFA Case
        if(match.getVersus() == 1){
            var playersAlive = match.getMatchPlayers().values().stream().filter(player -> player.isAlive());
            if(playersAlive.count() <= 1){
                var winner = playersAlive.findFirst().get();
                //win animation :)
                winnerAnimation(winner.getArenaPlayer().getArenaplayerUUID());
                //TODO: delete match send players to lobby


            }
        }

        return true;
    }

    public void winnerAnimation(String UUID){
        var player = Bukkit.getPlayer(UUID);
        Bukkit.broadcastMessage(player.getName().toString() + " WINS");
    }

}