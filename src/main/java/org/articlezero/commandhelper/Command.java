package org.articlezero.commandhelper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ArticleZero
 * @since 1/23/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    String name();
    String permission();
    //This is the message displayed when a user uses the command with incorrect usage
    String help() default "&cWrong usage, correct usage: &a%n%";
    String noPerms() default "&cYou don't have permission!";
    //This is if there is additional arguments, i.e /test command
    String addRequiredArgs() default "";
    //This is to add multiple permissions aside from the required one
    String[] addPermissions() default {};
    boolean allowConsole() default false;
    boolean tabCompletion() default true;
    int minArgs() default -1;
    //These are all the possible aliases _for the base command_
    String[] aliases() default {};

}
