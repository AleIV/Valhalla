package net.noobsters.core.paper.Arena;

import org.bukkit.inventory.ItemStack;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Kit {
    private ItemStack[] kit;
    private String name;
    private String mapTypeNeeded;
    
}
