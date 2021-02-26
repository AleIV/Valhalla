package net.noobsters.core.paper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.plugin.java.JavaPlugin;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.noobsters.core.paper.Practice.Arena;
import net.noobsters.core.paper.Practice.ArenaManager;
import net.noobsters.core.paper.Commands.arenaCMD;
import net.noobsters.core.paper.Commands.infoCMD;
import net.noobsters.core.paper.Commands.kitCMD;
import net.noobsters.core.paper.Commands.queueCMD;
import net.noobsters.core.paper.Commands.tpworldCMD;
import net.noobsters.core.paper.Listeners.ListenerManager;

//Valhalla where the dead warriors join the masses of those who have died in combat 

public class Valhalla extends JavaPlugin {
  // GUI tutorial: https://github.com/MrMicky-FR/FastInv
  // Scoreboard Tutorial: https://github.com/MrMicky-FR/FastBoard
  // Commands Tutorial: https://github.com/aikar/commands/wiki/Using-ACF

  private @Getter PaperCommandManager commandManager;
  private @Getter ArenaManager arenaManager;
  private @Getter ListenerManager listenerManager;

  private static @Getter Valhalla instance;

  @Override
  public void onEnable() {

    // worldcreator
    WorldCreator arenaWorld = new WorldCreator("valhalla");
    arenaWorld.environment(Environment.NORMAL);
    arenaWorld.type(WorldType.FLAT);
    arenaWorld.createWorld();

    instance = this;

    // managers
    commandManager = new PaperCommandManager(this);
    arenaManager = new ArenaManager(this);
    listenerManager = new ListenerManager(this);
        
    //commands
    commandManager.registerCommand(new arenaCMD(this));
    commandManager.registerCommand(new infoCMD(this));
    commandManager.registerCommand(new tpworldCMD(this));
    commandManager.registerCommand(new kitCMD(this));
    commandManager.registerCommand(new queueCMD(this));


    Location pos1 = new Location(Bukkit.getWorld("valhalla"), 20, 90, 20);
    Location pos2 = new Location(Bukkit.getWorld("valhalla"), -20, 80, -20);
    Location spawn1 = new Location(Bukkit.getWorld("valhalla"), 0, 81, 17);
    Location spawn2 = new Location(Bukkit.getWorld("valhalla"), 0, 81, -17);
    Location center = new Location(Bukkit.getWorld("valhalla"), 0, 81, 0);
    Arena arena = Arena.of("TEST-ARENA", "none", "NORMAL", pos1, pos2, spawn1, spawn2, center, 1, 82);
    arenaManager.getArenas().put("TEST-ARENA", arena);



        

    }

    @Override
    public void onDisable() {

  }
    
}