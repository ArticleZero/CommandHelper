package org.articlezero.commandhelper;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author ArticleZero
 * @since 1/23/15
 */
public class Information {

    private Player p = null;
    private CommandSender cs;
    private String[] args;

    public Information(CommandSender cs, String[] args){
        this.cs = cs;
        if(cs instanceof Player) this.p = (Player) cs;
        this.args = args;
    }

    /**
     * Checks if the CommandSender is a player
     * @return TRUE if the command sender is a player, FALSE otherwise
     */
    public boolean isPlayer(){
        return p != null;
    }

    /**
     * Gets the command sender
     * @return CommandSender
     */
    public CommandSender getCommandSender(){
        return cs;
    }

    /**
     * Gets the player who sent the command (If there is one)
     * @return A PLAYER who sent the command, or NULL if the command sender wasn't a player
     */
    public Player getPlayer(){
        return p;
    }

    /**
     * Checks if the command had any arguments passed to it
     * @return TRUE if it had args, FALSE otherwise
     */
    public boolean hasArgs(){
        return (args != null);
    }

    /**
     * Gets all the args if there are any.
     * @return A String[] of all the args, or NULL if there isn't any args
     */
    public String[] getArgs(){
        return args;
    }

    /**
     * Gets how many arguments there are indexed at 1
     * @return int of how many arguments there are (It will return 0 if there isn't any arguments)
     */
    public int argLength(){
        return args == null ? 0 : args.length;
    }

    /**
     * Checks if the
     * @param argument
     * @return
     */
    public boolean hasArg(String argument){
        for(String s:args){
            if(s.equalsIgnoreCase(argument)) return true;
        }
        return false;
    }

    /**
     * Gets the index from the required argument
     * @param argument - The argument to get the index for
     * @return A int of the index, or -1 if the argument wasn't found
     */
    public int getIndex(String argument){
        int counter = 0;
        for(String s:args){
            if(s.equalsIgnoreCase(argument)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    /**
     * Gets the argument based on the index passed
     * @param index - The index to use
     * @return A STRING of the argument, or NULL if the index doesn't exist
     */
    public String getArg(int index){
        if(index > args.length){
            return null;
        }else{
            return args[index];
        }
    }

    /**
     * Sends a chat message, color codes supported, to the command sender
     * @param msg - The message to send
     */
    public void sendMessage(String msg){
        cs.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

}
