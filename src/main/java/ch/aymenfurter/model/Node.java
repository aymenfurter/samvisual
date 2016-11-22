package ch.aymenfurter.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Node {
    private String desc;

    public String getName() {
        return desc;
    }

    public void setName(String name) {
        this.desc = name;
    }
    
    @Override
    public String toString() {
        return "Node{" +
                "desc='" + desc +
                "'}";
    }
}
