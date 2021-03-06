package com.featurefactory.witjava.model;


import java.util.HashMap;
import java.util.Map;

public class ChatContext {
    private Map<String, String> values = new HashMap<>();

    /***
     * timezone is used only if you don’t provide a reference_time. In this case, we will calculate reference_time from timezone and the UTC time of the API server.
     * @param timezone ID of timezone <a href="http://docs.oracle.com/javase/6/docs/api/java/util/TimeZone.html#getAvailableIDs()">TimeZone</a>
     */
    public void setTimezone(String timezone){
        values.put("timezone", timezone);
    }

    /***
     * Local date and time of the user, ISO8601 format.
     * Make sure you don’t send a UTC time, which would defeat the goal of this field.
     * Example: "2014-10-30T12:18:45-07:00"
     * @param referenceTime Local date and time of the user, ISO8601 format.
     */
    public void setReferenceTime(String referenceTime){
        values.put("reference_time", referenceTime);
    }

    /**
     * Resets all fields in context, including timezone and reference_time
     */
    public void resetContext(){
        values.clear();
    }

    /**
     * Sets the value to context
     * @param key key
     * @param value string value
     */
    public void setValue(String key, String value){
        values.put(key, value);
    }


    /**
     * Check if context contains a value by given key
     * @param key key
     * @return
     */
    public boolean containsKey(String key){
        return values.containsKey(key);
    }

    /**
     * Get value from context
     * @param key key to get
     * @return value
     */
    public String getValue(String key) {
        return values.get(key);
    }

    /**
     * Get and remove value
     * @param key
     * @return
     */
    public String popValue(String key){
        return values.remove(key);
    }

    @Override
    public String toString() {
        String val = "{";
        for(String key: values.keySet()){
            val+="\""+key+"\":\""+values.get(key)+"\"";
        }
        val += "}";
        return val;
    }
}
