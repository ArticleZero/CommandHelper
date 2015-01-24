package org.articlezero.commandhelper;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author ArticleZero
 * @since 1/23/15
 */
public class Parser {

    private static Set<CmdData> commands = new HashSet<>();
    private static Plugin p;
    private Handler handle = new  Handler();

    public void loadCommands(Class<?> clazz, Plugin plugin) {
        p = plugin;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                Command cmd = method.getAnnotation(Command.class);
                commands.add(new CmdData(cmd, clazz, method));
                PluginCommand pc = getCommand(cmd.name());
                pc.setExecutor(handle);
                pc.setTabCompleter(handle);
                pc.setAliases(Arrays.asList(cmd.aliases()));
            }
        }
    }

    public void loadCommands(Class<?>[] clazz, Plugin plugin) {
        p = plugin;
        for(Class c:clazz){
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Command.class)) {
                    Command cmd = method.getAnnotation(Command.class);
                    commands.add(new CmdData(cmd, c, method));
                    PluginCommand pc = getCommand(cmd.name());
                    pc.setExecutor(handle);
                    pc.setTabCompleter(handle);
                    pc.setAliases(Arrays.asList(cmd.aliases()));
                }
            }
        }
    }

    private PluginCommand getCommand(String name){
        String alias = name.toLowerCase();
        PluginCommand command = Bukkit.getServer().getPluginCommand(alias);
        if(command == null || command.getPlugin() != this) {
            command = Bukkit.getServer().getPluginCommand(p.getDescription().getName().toLowerCase() + ":" + alias);
        }
        return command != null && command.getPlugin() == this?command:null;
    }

    public static Set<CmdData> getCommands(){
        return commands;
    }


}
