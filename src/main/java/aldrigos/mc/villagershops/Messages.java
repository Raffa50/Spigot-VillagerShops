package aldrigos.mc.villagershops;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum Messages {
    NO_PERM(ChatColor.RED+"[VS]No permission for %s"),
    PARAM_MISS(ChatColor.RED+"[VS]Missing parameter. Usage: %s"),
    PARAM_INVALID(ChatColor.RED+"[VS]Invalid parameter %s"),
    PLAYERCMD("[VS]This is a player command!"),
    SHOP_EXIST("[VS]There is already a shop with id %s"),
    SHOP_NOT_EXIST("[VS]Shop %s does not exist");

    private final String msg;

    Messages(String message){
        msg = message;
    }

    @Override
    public String toString(){
        return msg;
    }

    public void send(CommandSender to, Object... args){
        to.sendMessage(String.format(this.toString(), args));
    }
}
