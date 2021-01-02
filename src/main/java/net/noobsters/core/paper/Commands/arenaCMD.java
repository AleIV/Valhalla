package net.noobsters.core.paper.Commands;

import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.noobsters.core.paper.Valhalla;

@RequiredArgsConstructor
@CommandAlias("valhalla")
public class arenaCMD extends BaseCommand {

    private @NonNull Valhalla instance;

    @CommandPermission("arena.cmd")
    @Subcommand("check")
    public void checkIsArenaPlayer(Player sender) {
        var uuid = sender.getUniqueId().toString();
        var isArenaPlayer = instance.getArenaManager().getArenaPlayers().containsKey(uuid);

        sender.sendMessage(isArenaPlayer ? (ChatColor.GREEN + sender.getName().toString() + " is an arena player.") 
            : (ChatColor.RED + sender.getName().toString() + " is not an arena player."));
    }


}