package org.articlezero.commandhelper;

import org.bukkit.ChatColor;

/**
 * @author ArticleZero
 * @since 1/24/15
 */
public enum Lang {

    NO_PERMS("&cError: You do not have permission!"),
    NOT_A_PLAYER("&4This command can only be used by players!");

    private String lang;
    Lang(String lang){
        this.lang = lang;
    }

    @Override
    public String toString(){
        return ChatColor.translateAlternateColorCodes('&', this.lang);
    }
}
