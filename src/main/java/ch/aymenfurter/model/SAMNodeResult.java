package ch.aymenfurter.model;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;



@XmlRootElement
public class SAMNodeResult {

    private List<SAMNode> nodes;

    public SAMNodeResult() {
        this(Lists.<SAMNode>newArrayList());
    }

    public SAMNodeResult(List<SAMNode> samNodes) {
        this.nodes = samNodes;
    }

    public List<SAMNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<SAMNode> samNodes) {
        this.nodes = samNodes;
    }

    @Override
    public String toString() {
        return "SAMNodeResult{" +
                "nodes=" + nodes +
                '}';
    }
}
