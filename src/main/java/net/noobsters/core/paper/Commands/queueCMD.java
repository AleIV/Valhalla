package net.noobsters.core.paper.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.noobsters.core.paper.Valhalla;
import net.noobsters.core.paper.Arena.Arena;
import net.noobsters.core.paper.Arena.ArenaManager;
import net.noobsters.core.paper.Arena.ArenaPlayer;

@RequiredArgsConstructor
@CommandAlias("queue")
public class queueCMD extends BaseCommand {

    private @NonNull Valhalla instance;

    @Subcommand("join")
    public void joinQueue(Player sender, String kit) {

        var senderUUID = sender.getUniqueId().toString();
        //check if sender is already in queue
        if(instance.getArenaManager().getArenaPlayers().get(senderUUID).isInQueue()){
            sender.sendMessage(ArenaManager.ALREADY_IN_QUEUE);
            return;
        }
        //check if kit exist
        if(!instance.getArenaManager().getKits().containsKey(kit)){
            sender.sendMessage(ArenaManager.KIT_NOT_FOUND);
            return;
        }

        //choose queue list
        ArrayList<ArenaPlayer> waitingList;
        if(!instance.getArenaManager().getQueue().containsKey(kit)){
            waitingList = new ArrayList<>();
            instance.getArenaManager().getQueue().put(kit, waitingList);

        } else{
            waitingList = instance.getArenaManager().getQueue().get(kit);
        }

        //check & choose if arena is available
        Arena selectedArena = null;

        for(var entry : instance.getArenaManager().getArenas().entrySet()){
            var arena = entry.getValue();
            if(!arena.isInUse()){
                arena.setInUse(true);
                selectedArena = arena;
                break;
            }
        }

        //find oponent

        ArenaPlayer opponent = null;

        if(!instance.getArenaManager().getArenaPlayers().isEmpty()){
            for(var entry : instance.getArenaManager().getArenaPlayers().entrySet()){
                var playerOpponent = entry.getValue();
                if(playerOpponent.isInQueue()){
                    
                }
            }   
        }

        //queue actions
        
        if(selectedArena == null){
            //not enough arenas
            sender.sendMessage(ArenaManager.NOT_AVAILABLE_ARENA);
        }else if(opponent == null || !Bukkit.getPlayer(opponent.getArenaplayerUUID()).isOnline()){
            //cant find opponent
            sender.sendMessage(ArenaManager.NOT_AVAILABLE_OPPONENTS);
        }else{

            //successful everything requirements to match

            //saca al opponent 
            //crea match
            //crea ingame players

            return;
        }

        //joined queue (only cases where cant match right now)
        sender.sendMessage(ArenaManager.JOINED_QUEUE);
        ArenaPlayer senderArenaPlayer = instance.getArenaManager().getArenaPlayers().get(sender.getUniqueId().toString());
        waitingList.add(senderArenaPlayer);
        senderArenaPlayer.setInQueue(true);
        senderArenaPlayer.setQueue(kit);

    }


    @Subcommand("leave")
    public void leaveQueue(Player sender) {
        
    }



}