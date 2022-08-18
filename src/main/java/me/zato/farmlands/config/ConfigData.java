package me.zato.farmlands.config;

public class ConfigData {

    private String key;
    private String value;

    private String comment;
    private String description;
    private boolean isdescription;

    public ConfigData(String key, String value){
        this.key = key;
        this.value = value;
    }

    public ConfigData(String description){
        isdescription = true;
        this.description = description;
    }

    public boolean isValid(){
        if(key == null || value == null)
            return false;
        else if(key.length() > 0 && value.length() > 0)
            return true;
        return false;
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        String v = value;
        if(v.startsWith("\"") && v.endsWith("\""))
            v = value.substring(1, v.length() - 1);
        return v;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }

    public String getInline(){
        if(!isDescription())
            if(hasComment())
                return (getKey() + ": " + getString() + " # " + comment);
            else
                return (getKey() + ": " + getString());
        if(!getDescription().equals(""))
            return ("# " + getDescription());
        return ("");
    }

    public String getString(){
        return value;
    }

    public boolean getBoolean(){
        return Boolean.parseBoolean(value);
    }

    public int getInt(){
        return Integer.parseInt(value);
    }

    public double getDouble(){
        return Double.parseDouble(value);
    }

    public void setValue(Object value){
        this.value = value.toString();
    }

    public String getDescription(){
        return description;
    }

    public boolean isDescription(){
        return isdescription;
    }

    public boolean hasComment(){
        return (comment != null);
    }
}
