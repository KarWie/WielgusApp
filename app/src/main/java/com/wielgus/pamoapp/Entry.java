package com.wielgus.pamoapp;

/**
 * Created by lazyFace on 2015-06-18.
 */
public class Entry {
    private long id;
    private String entry;

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return entry;
    }
}