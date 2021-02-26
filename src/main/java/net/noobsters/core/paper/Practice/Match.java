package net.noobsters.core.paper.Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;

import lombok.Data;
import net.noobsters.core.paper.Valhalla;

@Data
public class Match {
    private String matchID;
    private HashMap<Integer, List<InArenaPlayer>> matchPlayers;
    private Integer rounds;
    private Integer versus = 1;
    // versus 1 = FFA
    // versus = cantidad de equipos
    private String arenaID;
    private String kitID;
    private Integer timer;

    // team ID = equipo al que pertenece

    // si existe winner return X y si no 0
    /*
    public Integer distinct(){
        var teamsAlive = matchPlayers.values().stream().filter(p -> !p.isAlive()).map(p -> p.getTeamID()).distinct().collect(Collectors.toList());
        var count = teamsAlive.size();

        if(count == 1) teamsAlive.get(0);
        
        return 0;
    }



    public Integer getWinner() {
        var playersAlive = matchPlayers.values().stream().filter(p -> !p.isAlive());
        val teamID = new AtomicInteger();
        val hasSomeoneWon = new AtomicBoolean(true);

        playersAlive.forEach(player -> {
            if (teamID.get() == 0) {
                teamID.set(player.getTeamID());
            } else if (teamID.get() != player.getTeamID()) {
                hasSomeoneWon.set(false);
                return;
            }
        });

        return hasSomeoneWon.get() ? teamID.get() : 0;

    }

    public Integer getWinnerLamda() {
        var map = new HashMap<Integer, Integer>();
        matchPlayers.values().stream().filter(p -> !p.isAlive())
                .forEach(p -> map.put(p.getTeamID(), map.getOrDefault(p.getTeamID(), 0) + 1));

        return map.size() > 1 ? 0 : map.get(0);
    }*/

    public void performMatch(){
        // method to restart or start the match

        //loop for all match players
        var arena = Valhalla.getInstance().getArenaManager().getArenas().get(arenaID);
        for (var players : matchPlayers.entrySet()){
            var randomLocation = arena.getRandomLoc();
            var currentTeam = 1;
            for(var inArenaPlayer : players.getValue()){
                var player = Bukkit.getPlayer(UUID.fromString(inArenaPlayer.getArenaPlayer().getArenaplayerUUID()));
                if(matchPlayers.size() <= 2){
                    //2 teams max case

                    //inventory
                    inArenaPlayer.wearKit(kitID);
                    
                    //teleport
                    var teamID = inArenaPlayer.getTeamID();
                    if(teamID == 1){
                        player.teleport(arena.getSpawn1());
                    }else if(teamID == 2){
                        player.teleport(arena.getSpawn2());
                    }

                }else{
                    //more than 2 teams case

                    //inventory
                    inArenaPlayer.wearKit(kitID);
                    
                    //teleport
                    arena.teleportRandom(player);

                }
            }
        }

    }

    public Match(String matchID, ArenaPlayer[] players, Integer rounds, Integer versus, String arenaID, String kitID) {
        // crea ingame players

        // crea match
        this.matchID = matchID;
        this.matchPlayers = new HashMap<>();
        this.rounds = rounds;
        this.versus = versus;
        this.kitID = kitID;
        this.arenaID = arenaID;

        // buscar forma de elegir y dividir los players
        if (versus == 1) {
            // FFA MATCH
            var teamCount = 1;
            for (var player : players) {
                player.setMatch(matchID);
                var inArenaPlayer = InArenaPlayer.of(player, true, teamCount, matchID);
                matchPlayers.put(teamCount, new ArrayList<>());
                matchPlayers.get(teamCount).add(inArenaPlayer);

                teamCount++;
            }

        } else if (versus > 1) {
            // VERSUS MATCH

            float divFloat = players.length / versus;
            int divInt = (int) players.length / versus;
            float calc = divFloat - divInt >= 0.5 ? 1 : 0;
            var teamSize = divInt + calc;
            var teamCount = 0;
            var teamAssign = 1;
            for (var player : players) {

                player.setMatch(matchID);

                var inArenaPlayer = InArenaPlayer.of(player, true, teamAssign, matchID);

                if(matchPlayers.containsKey(teamAssign)){
                    matchPlayers.get(teamAssign).add(inArenaPlayer);
                }else{
                    matchPlayers.put(teamAssign, new ArrayList<>());
                    matchPlayers.get(teamAssign).add(inArenaPlayer);
                }

                if (teamCount < teamSize) {
                    teamCount++;
                } else if (teamCount == teamSize) {
                    teamCount = 0;
                    teamAssign++;
                }
            }

        }

    }

    // versus de 2 listas
    public Match(String matchID, ArenaPlayer[] players1, ArenaPlayer[] players2, Integer rounds, String arenaID,
            String kitID) {
        // crea ingame players

        // crea match
        this.matchID = matchID;
        this.matchPlayers = new HashMap<>();
        this.rounds = rounds;
        this.versus = 2;
        this.kitID = kitID;
        this.arenaID = arenaID;

        // VERSUS MATCH

        // set team and in arenaplayer for players 1
        for (var player : players1) {
            player.setMatch(matchID);
            var inArenaPlayer = InArenaPlayer.of(player, true, 1, matchID);
            matchPlayers.put(1, new ArrayList<>());
            matchPlayers.get(1).add(inArenaPlayer);
        }

        // set team and in arenaplayer for players 2
        for (var player : players2) {
            player.setMatch(matchID);
            var inArenaPlayer = InArenaPlayer.of(player, true, 2, matchID);
            matchPlayers.put(2, new ArrayList<>());
            matchPlayers.get(2).add(inArenaPlayer);
        }

    }

}
