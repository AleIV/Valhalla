package net.noobsters.core.paper;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.plugin.java.JavaPlugin;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.noobsters.core.paper.Arena.ArenaManager;
import net.noobsters.core.paper.Commands.tpworldCMD;

//Valhalla where the dead warriors join the masses of those who have died in combat 
 

  
public class Valhalla extends JavaPlugin{
    // GUI tutorial: https://github.com/MrMicky-FR/FastInv
    // Scoreboard Tutorial: https://github.com/MrMicky-FR/FastBoard
    // Commands Tutorial: https://github.com/aikar/commands/wiki/Using-ACF

    private @Getter PaperCommandManager commandManager;
    private @Getter ArenaManager arenaManager;

    private static @Getter Valhalla instance;

    @Override
    public void onEnable() {

        //worldcreator
        WorldCreator arenaWorld = new WorldCreator("Valhalla");
        arenaWorld.environment(Environment.NORMAL);
        arenaWorld.type(WorldType.FLAT);
        arenaWorld.createWorld();

        WorldCreator lobby = new WorldCreator("lobby");
        lobby.environment(Environment.THE_END);
        lobby.createWorld();

        instance = this;

        //managers
        commandManager = new PaperCommandManager(this);
        arenaManager = new ArenaManager(this);

        //commands
        commandManager.registerCommand(new tpworldCMD(this));





        

    }

    @Override
    public void onDisable() {

  }
    
}