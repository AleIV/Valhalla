package net.noobsters.core.paper.Practice;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import net.noobsters.core.paper.Valhalla;

@AllArgsConstructor(staticName = "of")
public class ArenaManager {
    Valhalla instance;
    
    public static String KIT_NOT_FOUND = ChatColor.RED + "Kit doesn't exist.";
    public static String NOT_AVAILABLE_ARENA = ChatColor.RED + "No arenas available.";
    public static String ALREADY_IN_QUEUE = ChatColor.RED + "You are already in queue.";
    public static String NOT_AVAILABLE_OPPONENTS = ChatColor.RED + "No opponents found.";
    public static String JOINED_QUEUE = ChatColor.YELLOW + "You have joined the queue.";
    public static String LEAVE_QUEUE = ChatColor.RED + "You have left the queue.";


    //volatiles
    private @Getter @Setter HashMap<String, ArenaPlayer> arenaPlayers = new HashMap<>();
    private @Getter @Setter HashMap<String, ArenaPlayer> queue = new HashMap<>();
    private @Getter @Setter HashMap<String, Match> matches = new HashMap<>();

    //saved database
    private @Getter @Setter HashMap<String, Kit> kits = new HashMap<>();
    //every practice should have an ID for scale?
    private @Getter @Setter HashMap<String, Arena> arenas = new HashMap<>();


    public ArenaManager(Valhalla instance) {
        this.instance = instance;
    }

}
