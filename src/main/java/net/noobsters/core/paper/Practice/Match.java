package net.noobsters.core.paper.Practice;

import java.util.HashMap;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Match {
    private String matchID = UUID.randomUUID().toString();
    private Integer timer;
    private Integer rounds;
    private HashMap<String, InArenaPlayer> matchPlayers = new HashMap<>();
    private Integer versus = 1; //Versus 2, 3, 4,5,6,7...

    //versus 1 = FFA
    //versus = cantidad de equipos
    private Arena arena;
    private Kit kit;


}
