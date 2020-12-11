package net.noobsters.core.paper.Arena;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.noobsters.core.paper.Valhalla;

@Data
@AllArgsConstructor(staticName = "of")
public class ArenaManager {
    Valhalla instance;
    private HashMap<String, Match> matches = new HashMap<>();

    public ArenaManager(Valhalla instance) {
        this.instance = instance;
        //arenaplayers
        //matches
        //arenas
        //kits


    }

}
