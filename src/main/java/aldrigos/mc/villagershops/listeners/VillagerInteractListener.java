package aldrigos.mc.villagershops.listeners;

import aldrigos.mc.villagershops.Messages;
import aldrigos.mc.villagershops.VillagerShopsPlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.logging.Logger;

public class VillagerInteractListener implements Listener {
    private final VillagerShopsPlugin p;
    private final Logger log;

    public VillagerInteractListener(VillagerShopsPlugin plugin){
        p = plugin;
        log = p.getServer().getLogger();
    }

    @EventHandler
    public void onPlayerShop(PlayerInteractEntityEvent e){
        if(!p.playerVsInfo.contains(e.getPlayer().getUniqueId()))
            return;

        var entity = e.getRightClicked();
        if(!(entity instanceof Villager))
            return;

        String shopid = entity.getPersistentDataContainer()
                .getOrDefault(new NamespacedKey(p, "shopid"), PersistentDataType.STRING, "");

        Messages.SHOP_INFO.send(e.getPlayer(), shopid);

        p.playerVsInfo.remove(e.getPlayer().getUniqueId());
    }
}
