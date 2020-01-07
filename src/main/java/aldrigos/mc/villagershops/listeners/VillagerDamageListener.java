package aldrigos.mc.villagershops.listeners;

import aldrigos.mc.villagershops.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

public class VillagerDamageListener implements Listener {
    private final VillagerShopsPlugin p;

    public VillagerDamageListener(VillagerShopsPlugin plugin){
        p = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(p.manager.isShop(e.getEntity().getUniqueId()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onZombie(EntityTransformEvent e){
        if(p.manager.isShop(e.getEntity().getUniqueId()))
            e.setCancelled(true);
    }
}
