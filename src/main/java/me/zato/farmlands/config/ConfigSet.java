package me.zato.farmlands.config;

import me.zato.farmlands.Farmlands;

import java.util.ArrayList;

import static me.zato.farmlands.config.ConfigUtility.addData;

public class ConfigSet {
    public static ArrayList<String> getDefaultConfig(){
        ArrayList<String> fileLines = new ArrayList<>();

        //default config
        fileLines.add(addData("Configuration for " + Farmlands.getPlugin().getDescription().getName() + " " + Farmlands.getPlugin().getDescription().getVersion() + " by ZatoZalez.").getInline());
        fileLines.add(addData("This plugin is custom build for Farmlands.").getInline());
        fileLines.add(addData("").getInline());

        fileLines.add(addData("This config should be named after your default world (spawn) in order to improve plugin features.").getInline());
        fileLines.add(addData("world", "world").getInline());

        //fileLines.add(addData("This is an example of a description.").getInline());
        //fileLines.add(addData("BooleanExample", true).getInline());
        //fileLines.add(addData("IntegerOrDoubleExample", 10).getInline());
        //fileLines.add(addData("DoubleExample", 1.5).getInline());
        //fileLines.add(addData("StringExample", "Hello World").getInline());
        //fileLines.add(addData("StringWithQuotation", "\"Hello World\"").getInline());
        //fileLines.add(addData("CommentBehindValue", "Value", "This is a comment and will be ignored.").getInline());
        //fileLines.add(addData("").getInline()); //empty line
        //--
        return fileLines;
    }
}
