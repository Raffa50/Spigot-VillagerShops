package aldrigos.mc.villagershops;

import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CommandCompleter implements TabCompleter {
    private static final List<String>
        subCommands = Arrays.asList("new", "delete", "?", "help", "list", "goto", "add", "remove", "info");

    private final ShopsManager manager;
    private final Logger log;

    public CommandCompleter(ShopsManager manager, Logger logger){
        this.manager = manager;
        this.log = logger;
    }

    private List<String> getMaterials(String startsWith){
        return Arrays.stream(Material.values())
                .map(m -> m.toString().toLowerCase())
                .filter(s -> s.startsWith(startsWith.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<String> getShopIds(String startsWith){
        return manager.getShops().stream()
                .map(s -> s.getId())
                .filter(s -> s.startsWith(startsWith))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String l, String[] args) {
        if(args.length < 1)
            return subCommands;

        switch(args[0].toLowerCase()){
            case "delete":
            case "goto":
                return getShopIds(args.length > 1 ? args[1] : "");

            case "new":
                if(args.length >= 4)
                    return Arrays.stream(Villager.Profession.values()).map(s -> s.toString()).collect(Collectors.toList());
                return null;

            case "remove":
                if(args.length <= 2)
                    return getShopIds(args.length > 1 ? args[1] : "");
                return null;

            case "add": // /vshop add <id> <qty> <request> <qty> <result>
                switch(args.length){
                    case 2:
                        return getShopIds(args.length > 1 ? args[1] : "");
                    case 4:
                        return getMaterials(args[3]);
                    case 6:
                        return getMaterials(args[5]);
                }
                return null;

            default:
                return args.length < 2 ? subCommands : null;
        }
    }
}
