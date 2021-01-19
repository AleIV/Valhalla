package net.noobsters.core.paper.Commands;

import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.noobsters.core.paper.Valhalla;
import net.noobsters.core.paper.Practice.Arena;
import net.noobsters.core.paper.Practice.ArenaManager;
import net.noobsters.core.paper.Practice.ArenaPlayer;

@RequiredArgsConstructor
@CommandAlias("queue")
public class queueCMD extends BaseCommand {

    private @NonNull Valhalla instance;

    @Subcommand("join")
    public void joinQueue(Player sender, String kit) {

        var arenaManager = instance.getArenaManager();
        var playerUUID = sender.getUniqueId().toString();
        //check if sender is already in queue
        if(arenaManager.getQueue().containsKey(playerUUID)){
            sender.sendMessage(ArenaManager.ALREADY_IN_QUEUE);
            return;
        }
        //check if kit exist
        if(!arenaManager.getKits().containsKey(kit)){
            sender.sendMessage(ArenaManager.KIT_NOT_FOUND);
            return;
        }

        //check & choose if arena is available

        var arenaTypeNeeded = arenaManager.getKits().get(kit).getMapTypeNeeded();
        var selectedArena = arenaManager.getArenas().values().stream().filter(arena -> isAvailableArena(arena, arenaTypeNeeded)).findFirst();

        //find oponent

        var opponent = arenaManager.getQueue().values().stream().filter(availableOpponent -> isAvailableOpponent(availableOpponent, kit)).findFirst();
        
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

            //saca al opponent 
            arenaManager.getQueue().remove(opponentUUID);

            //crea ingame players

            //crea match
            //var match = Match.of(matchID, timer, rounds, players, arena, kit);
            //arenaManager.getMatches().put(key, match);

            return;
        }

        //joined queue (only cases where cant match right now)
        sender.sendMessage(ArenaManager.JOINED_QUEUE);
        arenaManager.getQueue().put(playerUUID, senderArenaPlayer);
        senderArenaPlayer.setQueue(kit);

    }

    @Subcommand("leave")
    public void leaveQueue(Player sender) {
        var senderArenaPlayer = instance.getArenaManager().getArenaPlayers().get(sender.getUniqueId().toString());
        //leave any queue
        senderArenaPlayer.setQueue("none");
        
        
    }

    public boolean isAvailableArena(Arena arena, String arenaType){
        if(arena.notInUse() && arena.getMapType() == arenaType)
            return true;
        return false;
    }

    public boolean isAvailableOpponent(ArenaPlayer arenaPlayer, String queue){
        if(arenaPlayer.getQueue() == queue)
            return true;
        
        return false;
    }



}