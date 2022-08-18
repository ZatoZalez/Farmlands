package me.zato.farmlands.communication;

public class Messages {
    //default
    public static String new_player_join = "Welcome §6${PLAYER}§f to Farmlands!\nUse §6/farm§f to get started.";
    public static String player_join = "Welcome back, §6${PLAYER}§f!";
    public static String player_teleport_farm = "Teleporting you to your farm...";
    public static String player_bought_farm = "You bought a farm!";
    public static String player_generating_farm = "Generating farm...";

    public static String player_has_no_farm = "You do not have a farm. Type §6/farm§f to buy a farm.";
    public static String player_rename_through_chat = "Type your new farm name in chat. Type §6cancel§f to cancel your renaming.";
    public static String player_rename_cancel = "You have canceled your farm renaming.";

    //warnings
    public static String warning_player_illegally_break_block = "This is not your farm. You can't break blocks here without permission.";
    public static String warning_player_illegally_place_block = "This is not your farm. You can't place blocks here without permission.";
    public static String warning_player_illegally_interact_block = "This is not your farm. You can't interact here without permission.";
    public static String warning_player_illegally_interact_armorstand = "This is not your farm. You can't modify armorstands here without permission.";
    public static String warning_player_illegally_damage_entity = "This is not your farm. You can't break damage entities here without permission.";
    public static String warning_buying_farm = "You already bought a farm. Use §6/farm§f to visit your farm";

    public static String warning_rename_more_than_20_char = "You can't have more than §620§f characters in your farm name.";
    public static String warning_rename_less_than_5_char = "You can't have less than §62§f characters in your farm name.";
    public static String warning_rename_more_than_2_words = "You can't have more than §62§f words in your farm name.";

    //errors
    public static String error_generating_farm = "Something went wrong generating your farm! Please try again later.";
    public static String error_corrupted_farm = "Your farm is corrupted. Too bad!";

    //debug
    public static String debug_invalid_world = "World does not exist";
}
