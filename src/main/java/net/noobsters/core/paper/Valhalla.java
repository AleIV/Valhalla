package net.noobsters.core.paper;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.plugin.java.JavaPlugin;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.noobsters.core.paper.Arena.ArenaManager;
import net.noobsters.core.paper.Commands.arenaCMD;
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
    WorldCreator arenaWorld = new WorldCreator("Valhalla");
    arenaWorld.environment(Environment.NORMAL);
    arenaWorld.type(WorldType.FLAT);
    arenaWorld.createWorld();

    WorldCreator lobby = new WorldCreator("lobby");
    lobby.environment(Environment.NORMAL);
    lobby.type(WorldType.FLAT);
    lobby.createWorld();

    WorldCreator meetup = new WorldCreator("meetup");
    meetup.environment(Environment.NORMAL);
    meetup.type(WorldType.FLAT);
    meetup.createWorld();

    WorldCreator uhc = new WorldCreator("uhc");
    uhc.environment(Environment.NORMAL);
    uhc.type(WorldType.FLAT);
    uhc.createWorld();

    instance = this;

    // managers
    commandManager = new PaperCommandManager(this);
    arenaManager = new ArenaManager(this);
    listenerManager = new ListenerManager(this);
        
    //commands
    commandManager.registerCommand(new tpworldCMD(this));
    commandManager.registerCommand(new kitCMD(this));
    commandManager.registerCommand(new queueCMD(this));
    commandManager.registerCommand(new arenaCMD(this));





        

    }

    @Override
    public void onDisable() {

  }
    
}