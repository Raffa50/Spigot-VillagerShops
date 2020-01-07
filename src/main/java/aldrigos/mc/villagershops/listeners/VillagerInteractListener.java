package aldrigos.mc.villagershops.listeners;

import aldrigos.mc.villagershops.VillagerShopsPlugin;
import org.bukkit.Material;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

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
/*        var entity = e.getRightClicked();
        if(!(entity instanceof Merchant))
            return;

        var m = (Merchant) entity;
        var recipes = new ArrayList<MerchantRecipe>();

        var r = new MerchantRecipe(new ItemStack(Material.GOLD_INGOT), 999);
        r.addIngredient(new ItemStack(Material.EMERALD, 3));
        recipes.add(r);

        m.setRecipes(recipes);*/
    }

    @EventHandler
    public void onPlayerShop(InventoryClickEvent e){
        log.info("InventoryClickEvent");
    }
}
