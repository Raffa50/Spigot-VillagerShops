package aldrigos.mc.villagershops.commands;

import aldrigos.mc.villagershops.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.LinkedList;

class NewShopCommand implements SubCommand {
    private final ShopsManager manager;

    public NewShopCommand(ShopsManager m) {
        manager = m;
    }

    @Override
    public boolean execute(CommandSender sender, LinkedList<String> args) {
        if(args.size() < 2){
            Messages.PARAM_MISS.send(sender, "/vshop new <id> <displayname> [type]");
            return false;
        }
        if(!sender.hasPermission("vshop.admin.new")){
            Messages.NO_PERM.send(sender, "vshop.admin.new");
            return false;
        }
        if(!(sender instanceof Player)){
            Messages.PLAYERCMD.send(sender);
            return false;
        }
        var player = (Player)sender;

        Villager.Profession type = Villager.Profession.CLERIC;
        if(args.size() >= 3)
            try{
                type = Villager.Profession.valueOf(args.get(2).toUpperCase());
            }catch(Exception ex){
                Messages.PARAM_INVALID.send(sender, args.get(2));
                return false;
            }

        if(manager.existShopId(args.get(0))){
            Messages.SHOP_EXIST.send(player, args.get(0));
            return false;
        }

        manager.createShop(args.get(0), args.get(1), player.getLocation(), type);
        return true;
    }
}
