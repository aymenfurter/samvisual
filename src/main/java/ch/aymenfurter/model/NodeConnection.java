package ch.aymenfurter.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NodeConnection {
    private String sourceGraphName;
    private String targetGraphName;
    private int graphWeight; 
    
	public int getWeight() {
		return graphWeight;
	}
	public void setWeight(int weight) {
		this.graphWeight = weight;
	}
	public String getSourceGraphName() {
		return sourceGraphName;
	}
	public void setSourceGraphName(String sourceGraphName) {
		this.sourceGraphName = sourceGraphName;
	}
	public String getTargetGraphName() {
		return targetGraphName;
	}
	public void setTargetGraphName(String targetGraphName) {
		this.targetGraphName = targetGraphName;
	}
	
	@Override
    public String toString() {
        return "NodeConnection{" +
                "sourceGraphName='" + sourceGraphName +
                "', targetGraphName='" + targetGraphName + '\'' +
                ", graphWeight=" + graphWeight +
                '}';
    }
}
