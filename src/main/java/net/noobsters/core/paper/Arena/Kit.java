package net.noobsters.core.paper.Arena;

import org.bukkit.inventory.PlayerInventory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Kit {
    private PlayerInventory kit;
    private String name;
    private String mapTypeNeeded;

    
}
