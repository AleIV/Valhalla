package net.noobsters.core.paper;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

/**
 * Valhalla where the dead warriors join the masses of those who have died in combat 
 * and prepare to aid Odin during the events of Ragnarök.
 */
public class Valhalla extends JavaPlugin{
    // GUI tutorial: https://github.com/MrMicky-FR/FastInv
    // Scoreboard Tutorial: https://github.com/MrMicky-FR/FastBoard
    // Commands Tutorial: https://github.com/aikar/commands/wiki/Using-ACF
    
    //private @Getter PaperCommandManager commandManager;

    private static @Getter Valhalla instance;

    @Override
    public void onEnable() {

        instance = this;

        //commandManager = new PaperCommandManager(this);
        //commandManager.registerCommand(new tpWorldCMD(this));

    }

    @Override
    public void onDisable() {

    }
    
}