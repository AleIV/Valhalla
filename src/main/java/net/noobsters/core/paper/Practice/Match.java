package net.noobsters.core.paper.Practice;

import java.util.HashMap;

import lombok.Data;

@Data
public class Match {
    private String matchID;
    private HashMap<String, InArenaPlayer> matchPlayers;
    private Integer rounds;
    private Integer versus = 1;
    //versus 1 = FFA
    //versus = cantidad de equipos
    private String arenaID;
    private String kitID;
    private Integer timer;

    // team ID = 0 FFA
    // team ID = equipo al que pertenece

    public Match(String matchID, ArenaPlayer[] players, Integer rounds, Integer versus, String arenaID, String kitID){
        //crea ingame players

        //crea match
        this.matchID = matchID;
        this.matchPlayers = new HashMap<>();
        this.rounds = rounds;
        this.versus = versus;
        this.kitID = kitID;
        this.arenaID = arenaID;


        //buscar forma de elegir y dividir los players
        if(versus == 1){
            //FFA MATCH
            for (int i = 0; i < players.length; i++) {
                players[i].setMatch(matchID);
                var inArenaPlayer = InArenaPlayer.of(players[i], true, 0, matchID);
                matchPlayers.put(players[i].getArenaplayerUUID(), inArenaPlayer);
            }

        }else if(versus > 1){
            //VERSUS MATCH

            float divFloat = players.length / versus;
            int divInt = (int) players.length / versus;
            float calc = divFloat-divInt >= 0.5 ? 1 : 0;
            var teamSize = divInt + calc;
            var teamCount = 0;
            var teamAssign = 1;
            for (int i = 0; i < players.length; i++) {

                players[i].setMatch(matchID);
                
                var inArenaPlayer = InArenaPlayer.of(players[i], true, teamAssign, matchID);

                matchPlayers.put(players[i].getArenaplayerUUID(), inArenaPlayer);

                if(teamCount < teamSize){
                    teamCount++;
                }else if(teamCount == teamSize){
                    teamCount = 0;
                    teamAssign++;
                }
            }

        }

    }

}
