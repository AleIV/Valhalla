package net.noobsters.core.paper.Commands;

import java.util.UUID;

import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.noobsters.core.paper.Valhalla;
import net.noobsters.core.paper.Practice.Arena;
import net.noobsters.core.paper.Practice.ArenaManager;
import net.noobsters.core.paper.Practice.ArenaPlayer;
import net.noobsters.core.paper.Practice.Match;

@RequiredArgsConstructor
@CommandAlias("queue")
public class queueCMD extends BaseCommand {

    private @NonNull Valhalla instance;

    @Subcommand("join")
    public void joinQueue(Player sender, String kitName, @Optional Integer rounds) {

        var arenaManager = instance.getArenaManager();
        var playerUUID = sender.getUniqueId().toString();
        //check if sender is already in queue
        if(arenaManager.getQueue().containsKey(playerUUID)){
            sender.sendMessage(ArenaManager.ALREADY_IN_QUEUE);
            return;
        }
        //check if kit exist
        if(!arenaManager.getKits().containsKey(kitName)){
            sender.sendMessage(ArenaManager.KIT_NOT_FOUND);
            return;
        }

        //check & choose if arena is available

        var arenaTypeNeeded = arenaManager.getKits().get(kitName).getMapTypeNeeded();
        var selectedArena = arenaManager.getArenas().values().stream().filter(arena -> !isAvailableArena(arena, arenaTypeNeeded)).findAny();

        //find oponent

        var opponent = arenaManager.getQueue().values().stream().filter(availableOpponent -> isAvailableOpponent(availableOpponent, kitName)).findAny();
        
        //queue actions

        var senderArenaPlayer = arenaManager.getArenaPlayers().get(sender.getUniqueId().toString());

        if(!selectedArena.isPresent()){
            //not enough arenas joined queue
            sender.sendMessage(ArenaManager.NOT_AVAILABLE_ARENA);
        }else if(!opponent.isPresent()){
            //cant find opponent joined queue
            sender.sendMessage(ArenaManager.NOT_AVAILABLE_OPPONENTS);
        }else{
            //successful everything requirements to match

            var opponentArenaPlayer = arenaManager.getArenaPlayers().get(opponent.get().getArenaplayerUUID());
            var opponentUUID = opponentArenaPlayer.getArenaplayerUUID();

            var selectedArenaID = selectedArena.get().getArenaID();

            var selectedRounds = rounds == null ? 1 : rounds;
            //saca al opponent 
            arenaManager.getQueue().remove(opponentUUID);

            //crea match
            var matchID = UUID.randomUUID().toString();
            ArenaPlayer[] matchPlayers = new ArenaPlayer[] {
                opponentArenaPlayer, 
                senderArenaPlayer
            };

            var match = new Match(matchID, matchPlayers, selectedRounds, 1, selectedArenaID, kitName);
            arenaManager.getMatches().put(matchID, match);

            return;
        }

        //joined queue (only cases where cant match right now)
        arenaManager.getQueue().put(playerUUID, senderArenaPlayer);
        senderArenaPlayer.setQueue(kitName);
        sender.sendMessage(ArenaManager.JOINED_QUEUE);

    }

    @Subcommand("leave")
    public void leaveQueue(Player sender) {
        var arenaManager = instance.getArenaManager();
        var senderArenaPlayer = arenaManager.getArenaPlayers().get(sender.getUniqueId().toString());
        
        //leave any queue
        arenaManager.getQueue().remove(sender.getUniqueId().toString());
        senderArenaPlayer.setQueue("none");
        sender.sendMessage(ArenaManager.LEAVE_QUEUE);
        
        
    }

    public boolean isAvailableArena(Arena arena, String arenaType){
        return !arena.isInUse() && arena.getMapType() == arenaType;
    }

    public boolean isAvailableOpponent(ArenaPlayer arenaPlayer, String queue){
        return arenaPlayer.getQueue() == queue;
    }



}