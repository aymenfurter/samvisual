package ch.aymenfurter.model;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;



@XmlRootElement
public class SAMNodeConnectionResult {

    private List<SAMNodeConnection> nodesConnections;

    public SAMNodeConnectionResult() {
        this(Lists.<SAMNodeConnection>newArrayList());
    }

    public SAMNodeConnectionResult(List<SAMNodeConnection> samNodeConnections) {
        this.nodesConnections = samNodeConnections;
    }

    public List<SAMNodeConnection> getNodeConnections() {
        return nodesConnections;
    }

    public void setNodeConnections(List<SAMNodeConnection> samNodeConnections) {
        this.nodesConnections = samNodeConnections;
    }

    @Override
    public String toString() {
        return "SAMNodeConnectionResult{" +
                "nodesConnections=" + nodesConnections +
                '}';
    }
}
