package aldrigos.mc.villagershops;

import aldrigos.mc.villagershops.commands.VShopCommands;
import aldrigos.mc.villagershops.listeners.*;
import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VillagerShopsPlugin extends JavaPlugin {
    private static final String saveFile = "plugins/vshop/shops.json";

    public ShopsManager manager;

    private void load() throws IOException {
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

    private void save() throws IOException {
        var file = new File(saveFile);
        file.mkdir();
        var json = new Gson();
        Files.writeString(Paths.get(saveFile), json.toJson(manager));
    }

    @Override
    public void onEnable(){
        try{
            load();
        }catch (Exception ex){
            getServer().getLogger().throwing(VillagerShopsPlugin.class.getName(), "onEnable", ex);
            getServer().getLogger().severe("[VS]load error");
            setEnabled(false);
            return;
        }

        var pm = getServer().getPluginManager();
        //pm.registerEvents(new VillagerInteractListener(this), this);
        pm.registerEvents(new VillagerDamageListener(this), this);

        var vshopCmd = getCommand("vshop");
        vshopCmd.setExecutor(new VShopCommands(this));
        vshopCmd.setTabCompleter(new CommandCompleter(manager));
    }

    @Override
    public void onDisable(){
        try {
            save();
        } catch (Exception ex) {
            getServer().getLogger().throwing(VillagerShopsPlugin.class.getName(), "onEnable", ex);
            getServer().getLogger().severe("[VS]save error");
        }
    }
}
