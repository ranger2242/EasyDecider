package quadx.decider10a;

import java.util.ArrayList;

/**
 * Created by Chris on 9/10/2017.
 */

public class Entry {
    private ArrayList<String> entries = new ArrayList<>();
    private int index=0;
    private String name= "name";

    public Entry(ArrayList<String> entries, int index, String name) {
        this.entries = entries;
        this.index = index;
        this.name = name;
    }

    public int getIndex() {

        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getEntries() {

        return entries;
    }

    public void setEntries(ArrayList<String> entries) {
        this.entries = entries;
    }
}
