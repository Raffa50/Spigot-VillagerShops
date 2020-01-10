package aldrigos.mc.villagershops;

import org.bukkit.command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandCompleter implements TabCompleter {
    private final ShopsManager manager;

    public CommandCompleter(ShopsManager manager){
        this.manager = manager;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if(!command.getName().toLowerCase().equals("vshop"))
            return null;

        if(args.length == 0)
            return Arrays.asList("new", "delete", "?", "help", "list", "goto", "add", "remove");

        switch(args[0].toLowerCase()){
            case "goto":
                var ret = new ArrayList<String>();
                for(var shop: manager.getShops())
                    ret.add(shop.name);
                return ret;
            case "new":

        }

        return null;
    }
}
