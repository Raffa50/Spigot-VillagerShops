package aldrigos.mc.villagershops;

import aldrigos.mc.villagershops.commands.VShopCommands;
import aldrigos.mc.villagershops.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class VillagerShopsPlugin extends JavaPlugin {
    public ShopsManager manager;

    @Override
    public void onEnable(){
        manager = new ShopsManager();
        manager.load(this);

        var pm = getServer().getPluginManager();
        //pm.registerEvents(new VillagerInteractListener(this), this);
        pm.registerEvents(new VillagerDamageListener(this), this);

        this.getCommand("vshop").setExecutor(new VShopCommands(this));
    }
}
