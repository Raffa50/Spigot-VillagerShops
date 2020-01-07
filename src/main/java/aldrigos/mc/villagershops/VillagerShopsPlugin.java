package aldrigos.mc.villagershops;

import aldrigos.mc.villagershops.commands.VShopCommands;
import aldrigos.mc.villagershops.listeners.*;
import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class VillagerShopsPlugin extends JavaPlugin {
    private static final String saveFile = "plugins/vshop/shops.json";

    public ShopsManager manager;

    private void load() throws Exception {
        var file = new File(saveFile);
        if(!file.exists())
            manager = new ShopsManager();
        else {
            var json = new Gson();
            try(var reader = new FileReader(file)){
                manager = json.fromJson(reader, ShopsManager.class);
            }
        }

        manager.load(this);
    }

    @Override
    public void onEnable(){
        try{
            load();
        }catch (Exception ex){
            getServer().getLogger().throwing(VillagerShopsPlugin.class.getName(), "onEnable", ex);
            getServer().getLogger().severe("[VS]loading error");
            setEnabled(false);
            return;
        }

        var pm = getServer().getPluginManager();
        //pm.registerEvents(new VillagerInteractListener(this), this);
        pm.registerEvents(new VillagerDamageListener(this), this);

        this.getCommand("vshop").setExecutor(new VShopCommands(this));
    }
}
