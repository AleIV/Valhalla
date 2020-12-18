package net.noobsters.core.paper.Arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.noobsters.core.paper.Valhalla;

@Data
@AllArgsConstructor(staticName = "of")
public class ArenaManager {
    Valhalla instance;
    private HashMap<String, Match> matches = new HashMap<>();
    private HashMap<String, ArenaPlayer> arenaPlayers = new HashMap<>();
    private HashMap<String, Arena> arenas = new HashMap<>();
    private List<Kit> kits = new ArrayList<>();


    public ArenaManager(Valhalla instance) {
        this.instance = instance;
        //arenaplayers
        //matches
        //arenas
        //kits


    }

}
