package me.zato.farmlands.config;

public class ConfigValue {
    public static String getWorldName(){
        return ConfigUtility.getData("world").getString();
    }
}
