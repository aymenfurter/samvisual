package ch.aymenfurter.model;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class NodeResult {
	private List<Node> nodes;

	public NodeResult() {
		//this(Lists.<Node>newArrayList());
	}

	public NodeResult(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	@Override
	public String toString() {
		return "NodeResult{" + "nodes=" + nodes + '}';
	}

}
