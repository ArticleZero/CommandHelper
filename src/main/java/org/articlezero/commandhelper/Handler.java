package org.articlezero.commandhelper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author ArticleZero
 * @since 1/23/15
 */
public class Handler implements CommandExecutor, TabCompleter{

    private Map<org.articlezero.commandhelper.Command, Method> commands = Parser.getCommands();
    help go()
    help hello test()
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        for(org.articlezero.commandhelper.Command command:commands.keySet()){
            if(cmd.getName().equalsIgnoreCase(command.name())){
                Information info = new Information(sender, args);
                if(args.length > 0){
                    if(info.hasArgs()){
                        if(args.length != info.argLength()) return true;
                        String[] cmdArgs = command.addRequiredArgs().split(" ");
                        int index = 0;
                        for(String s:cmdArgs){
                            if(!args[index].equalsIgnoreCase(s)) return true;
                            index++;
                        }
                        if(!info.isPlayer() && !command.allowConsole()){
                            info.sendMessage(ChatColor.RED + "This command can only be performed by players!");
                            return true;
                        }
                        if(!hasPermission(cs, command)){
                            info.sendMessage(ChatColor.RED + "Error: You don't have permission!");
                            return true;
                        }
                        try{
                            commands.get(command).invoke(CommandHandler.class.newInstance(), info);
                            return true;
                        }catch(Exception e){
                            Bukkit.getServer().getLogger().log(Level.SEVERE, "[OITQ] A critical error occured when attempting to perform a command...");
                            e.printStackTrace();
                            return true;
                        }
                    }
                }else{

                }
                if(!info.isPlayer() && !command.allowConsole()){
                    info.sendMessage(ChatColor.RED + "This command can only be used by players!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPermission(CommandSender sender, org.articlezero.commandhelper.Command cmd){
        if(sender.hasPermission(cmd.permission())) return true;
        if(cmd.addPermissions().length != 0){
            for(String s:cmd.addPermissions()){
                if(sender.hasPermission(s)) return true;
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
        return null;
    }
}
