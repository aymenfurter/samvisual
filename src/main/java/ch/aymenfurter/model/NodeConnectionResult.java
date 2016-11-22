package ch.aymenfurter.model;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

@XmlRootElement
public class NodeConnectionResult {
    private List<NodeConnection> nodesConnection;

    public NodeConnectionResult() {
        //this(Lists.<NodeConnection>newArrayList());
    }

    public NodeConnectionResult(List<NodeConnection> nodesConnection) {
        this.nodesConnection = nodesConnection;
    }

    public List<NodeConnection> getNodes() {
        return nodesConnection;
    }
    
    @Override
	public String toString() {
		return "NodeConnectionResult{" + "nodesConnection=" + nodesConnection + '}';
	}
}
