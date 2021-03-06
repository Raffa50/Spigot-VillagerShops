package aldrigos.mc.villagershops.commands;

import aldrigos.mc.villagershops.*;
import org.bukkit.command.*;

import java.util.Arrays;
import java.util.LinkedList;

public class VShopCommands implements CommandExecutor {
    private final VillagerShopsPlugin p;

    public VShopCommands(VillagerShopsPlugin plugin){
        p = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            Messages.PARAM_MISS.send(sender, "/vshop ? for help");
            return false;
        }

        var largs = new LinkedList<String>(Arrays.asList(args));
        final String sc = largs.pop();
        SubCommand ex;
        switch (sc){
            case "new":
                ex = new NewShopCommand(p.manager);
                break;
            case "delete":
                ex = new DeleteShopCommand(p.manager);
                break;
            case "add":
                ex = new AddTradeCommand(p.manager);
                break;
            case "remove":
                ex = new RemoveTradeCommand(p.manager);
                break;
            case "list":
                ex = new ListShopsCommand(p.manager);
                break;
            case "goto":
                ex = new GoToShopCommand(p.manager);
                break;
            case "info":
                ex = new InfoCommand();
                break;
            case "?":
            case "help":
                ex = new HelpCommand();
                break;
            default:
                Messages.PARAM_INVALID.send(sender, "/vshop "+sc);
                return false;
        }

        return ex.execute(sender, largs);
    }
}
