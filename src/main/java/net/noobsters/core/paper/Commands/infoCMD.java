package net.noobsters.core.paper.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Subcommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.noobsters.core.paper.Valhalla;

@RequiredArgsConstructor
@CommandPermission("info.cmd")
@CommandAlias("info")
public class infoCMD extends BaseCommand {

    private @NonNull Valhalla instance;

    @Subcommand("arenaplayers")
    public void arenaPlayersList(CommandSender sender) {
        var arenaPlayers = instance.getArenaManager().getArenaPlayers();
        var list = new StringBuilder();
        list.append(ChatColor.AQUA + "Arena Players list: ");
        if(arenaPlayers.isEmpty()){
            list.append("none");
        }else{
            for (var arenaPlayer : arenaPlayers.values()) {
                list.append(arenaPlayer.getArenaplayerName() + " ");
            }
        }

        sender.sendMessage(list.toString());
    }

    @Subcommand("arenas")
    public void arenaList(CommandSender sender) {
        var arenas = instance.getArenaManager().getArenas();
        var list = new StringBuilder();
        list.append(ChatColor.GREEN + "Arena list: ");
        for (var arena : arenas.values()) {
            list.append(arena.getArenaID() + " ");
        }

        sender.sendMessage(list.toString());
    }

    @Subcommand("kits")
    public void kitList(CommandSender sender) {
        var kits = instance.getArenaManager().getKits();
        var list = new StringBuilder();
        list.append(ChatColor.GREEN + "Kit list: ");
        if(kits.isEmpty()){
            list.append("none");
        }else{
            for (var kit : kits.values()) {
                list.append(ChatColor.AQUA + kit.getName() + " ");
            }
        }

        sender.sendMessage(list.toString());
    }

    @Subcommand("matches")
    public void matchList(CommandSender sender) {
        var matches = instance.getArenaManager().getMatches();
        var list = new StringBuilder();
        list.append(ChatColor.GREEN + "Match list: \n");
        if(matches.isEmpty()){
            list.append("none");
        }else{
            for (var match : matches.values()) {
                var players = new StringBuilder();
                for (var player : match.getMatchPlayers().values()){
                    var p = Bukkit.getPlayer(player.getArenaPlayer().getArenaplayerUUID());
                    list.append(player.isAlive() ? ChatColor.DARK_GREEN + p.getName().toString() : ChatColor.RED + p.getName().toString());
                }
                list.append(ChatColor.GREEN + match.getMatchID() + ": " + ChatColor.YELLOW + players.toString() + " \n");
            }
        }

        sender.sendMessage(list.toString());
    }

    @Subcommand("playerinfo")
    public void playerInfo(CommandSender sender, @Flags("other") OfflinePlayer target) {
        var arenaPlayers = instance.getArenaManager().getArenaPlayers();
        var targetUUID = target.getUniqueId().toString();
        var infoPlayer = new StringBuilder();
        infoPlayer.append(ChatColor.YELLOW + "Player: " + target.getName().toString());
        if(arenaPlayers.containsKey(targetUUID)){
            var targetArenaPlayer = arenaPlayers.get(targetUUID);
            infoPlayer.append(ChatColor.GREEN + " IS AN ARENA PLAYER \n");
            infoPlayer.append(ChatColor.AQUA + "UUID: " + targetArenaPlayer.getArenaplayerUUID() + "\n");
            infoPlayer.append(ChatColor.AQUA + "IS IN MATCH: " + targetArenaPlayer.getMatch() + "\n");
            infoPlayer.append(ChatColor.AQUA + "IS IN QUEUE: " + targetArenaPlayer.getQueue() + "\n");

        }else{
            infoPlayer.append(ChatColor.RED + "IS NOT AN ARENA PLAYER ");
        }
        
        sender.sendMessage(infoPlayer.toString());
    }
    
    @Subcommand("where")
    public void where(Player sender) {
        var arenaList = instance.getArenaManager().getArenas().values();
        var senderLoc = sender.getLocation();
        for (var arena : arenaList) {
            if(arena.isInCube(senderLoc)){
                sender.sendMessage(ChatColor.GREEN + "You are in arena ID: " + arena.getArenaID());
                return;
            }
        }
        sender.sendMessage(ChatColor.RED + "You are not in any arena.");

    }




}