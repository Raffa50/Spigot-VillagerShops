package aldrigos.mc.villagershops.commands;

import aldrigos.mc.villagershops.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class GoToShopCommand implements SubCommand {
    private final ShopsManager manager;

    public GoToShopCommand(ShopsManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean execute(CommandSender sender, LinkedList<String> args) {
        if(!sender.hasPermission("vshop.admin.goto")){
            Messages.NO_PERM.send(sender, "vshop.admin.goto");
            return false;
        }
        if(args.size() < 1){
            Messages.PARAM_MISS.send(sender, "/vshop goto <id>");
            return false;
        }
        var shop = manager.getById(args.get(0));
        if(shop == null){
            Messages.SHOP_NOT_EXIST.send(sender, args.get(0));
            return false;
        }
        if(!(sender instanceof Player)){
            Messages.PLAYERCMD.send(sender);
            return false;
        }
        var player = (Player)sender;

        player.teleport(shop.pos);
        return true;
    }
}
