package net.noobsters.core.paper.Practice;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Arena{
    private String arenaID = UUID.randomUUID().toString();
    private String inUseBy = "none"; //Match ID
    private String mapType;
    private Location pos1;
    private Location pos2;
    private Location spawn1;
    private Location spawn2;
    private Location center;
    private Integer mapSize;
    private Integer height;


    public boolean isInUse(){
        return !(inUseBy == "none");
    }

    public boolean isInCube(Location point){

        var cX = pos1.getX() < pos2.getX();
        var cY = pos1.getZ() < pos2.getZ();
        var cZ = pos1.getZ() < pos2.getZ();

        var minX = cX ? pos1.getX() : pos2.getX();
        var maxX = cX ? pos2.getX() : pos1.getX();

        var minY = cY ? pos1.getY() : pos2.getY();
        var maxY = cY ? pos2.getY() : pos1.getY();

        var minZ = cZ ? pos1.getZ() : pos2.getZ();
        var maxZ = cZ ? pos2.getZ() : pos1.getZ();

        if(point.getX() < minX || point.getY() < minY || point.getZ() < minZ) return false;
        if(point.getX() > maxX || point.getY() > maxY || point.getZ() > maxZ) return false;

        return true;
    }

    public boolean isInArea(Location point){
       
        var cX = pos1.getX() < pos2.getX();
        var cZ = pos1.getZ() < pos2.getZ();

        var minX = cX ? pos1.getX() : pos2.getX();
        var maxX = cX ? pos2.getX() : pos1.getX();

        var minZ = cZ ? pos1.getZ() : pos2.getZ();
        var maxZ = cZ ? pos2.getZ() : pos1.getZ();

        if(point.getX() < minX || point.getZ() < minZ) return false;
        if(point.getX() > maxX || point.getZ() > maxZ) return false;

        return true;
    }
    
    public Location getRandomLoc(){
        var loc = center.getBlock().getRelative(chooseCoord(16*mapSize), 0, chooseCoord(16*mapSize));
        return loc.getLocation();
    }

    public int chooseCoord(int radius) {
        var random = new Random();
        var num = random.nextInt(radius);
        num = random.nextBoolean() ? ~(num) : num;
        return num;
    }
}
