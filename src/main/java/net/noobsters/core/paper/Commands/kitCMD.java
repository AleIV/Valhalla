package net.noobsters.core.paper.Commands;

import org.bukkit.command.CommandSender;
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
import net.noobsters.core.paper.Practice.ArenaManager;
import net.noobsters.core.paper.Practice.Kit;

@RequiredArgsConstructor
@CommandPermission("kit.cmd")
@CommandAlias("kit")
public class kitCMD extends BaseCommand {

    private @NonNull Valhalla instance;

    @Subcommand("list")
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

    @Subcommand("create")
    public void kitCreateGlobal(Player sender, String name, String type) {

        if(instance.getArenaManager().getKits().containsKey(name)){
            sender.sendMessage(ArenaManager.KIT_OVERWRITTEN);
        }

        final ItemStack[] inventory = sender.getInventory().getContents().clone();
        Kit kit = Kit.of(inventory, name, type);
        instance.getArenaManager().getKits().put(name, kit);
        sender.getInventory().clear();
        sender.sendMessage(ChatColor.GREEN + "Global Kit saved as " + name + ".");
    }

    @Subcommand("remove")
    public void kitRemove(Player sender, String name) {

        if(!instance.getArenaManager().getKits().containsKey(name)){
            sender.sendMessage(ArenaManager.KIT_NOT_FOUND);
            return;
        }

        instance.getArenaManager().getKits().remove(name);
        sender.sendMessage(ChatColor.RED + "Kit " + name + " removed.");
    }

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