package aldrigos.mc.villagershops.commands;

import aldrigos.mc.villagershops.*;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;

public class DeleteShopCommand implements SubCommand {
    private final ShopsManager manager;

    public DeleteShopCommand(ShopsManager man){
        manager = man;
    }

    @Override
    public boolean execute(CommandSender sender, LinkedList<String> args) {
        if(args.isEmpty()){
            Messages.PARAM_MISS.send(sender, "/vshop delete <id>");
            return false;
        }
        if(!sender.hasPermission("vshop.admin.delete")){
            Messages.NO_PERM.send(sender, "vshop.admin.delete");
            return false;
        }

        String id = args.getFirst();
        if(!manager.existShopId(id)){
            Messages.SHOP_NOT_EXIST.send(sender, id);
            return false;
        }
        manager.deleteShop(id);
        Messages.SHOP_DELETED.send(sender);
        return true;
    }
}
