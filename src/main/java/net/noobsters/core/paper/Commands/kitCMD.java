package net.noobsters.core.paper.Commands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.noobsters.core.paper.Valhalla;
import net.noobsters.core.paper.Arena.ArenaManager;
import net.noobsters.core.paper.Arena.Kit;

@RequiredArgsConstructor
@CommandAlias("kit")
public class kitCMD extends BaseCommand {

    private @NonNull Valhalla instance;

    @CommandPermission("kit.cmd")
    @Subcommand("list")
    public void kitList(Player sender) {
        var kits = instance.getArenaManager().getKits().toString();
        sender.sendMessage(ChatColor.GREEN + kits);
    }

    @CommandPermission("kit.cmd")
    @Subcommand("global")
    public void kitCreateGlobal(Player sender, String name, String type) {

        if(!instance.getArenaManager().getKits().containsKey(name)){
            sender.sendMessage(ArenaManager.KIT_NOT_FOUND);
            return;
        }

        final ItemStack[] inventory = sender.getInventory().getContents().clone();
        Kit kit = Kit.of(inventory, name, type);
        instance.getArenaManager().getKits().put(name, kit);
        sender.getInventory().clear();
        sender.sendMessage(ChatColor.GREEN + "Global Kit saved as " + name + ".");
    }

    @CommandPermission("kit.cmd")
    @Subcommand("wear")
    public void kitWear(Player sender, String key) {

        if(!instance.getArenaManager().getKits().containsKey(key)){
            sender.sendMessage(ArenaManager.KIT_NOT_FOUND);
            return;
        }

        var kit = instance.getArenaManager().getKits().get(key);
        sender.getInventory().setContents(kit.getKit().clone());

        sender.sendMessage(ChatColor.YELLOW + "You are wearing " + kit.getName() + " kit.");
    }



}