package net.noobsters.core.paper.Commands;

import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.noobsters.core.paper.Valhalla;

@RequiredArgsConstructor
@CommandAlias("valhalla")
public class arenaCMD extends BaseCommand {

    private @NonNull Valhalla instance;

    @CommandPermission("arena.cmd")
    @Subcommand("arena")
    public void createArena(Player sender) {
        
    }


}