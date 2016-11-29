package ch.aymenfurter.elk.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregationBucket {
    @JsonProperty(value = "key")
    private String flowId;
    public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String sourceServer) {
		this.flowId = sourceServer;
	}
    @JsonProperty(value = "3")
	private AggregationBucketServerBucket listOfServers;
	public AggregationBucketServerBucket getListOfServers() {
		return listOfServers;
	}
	public void setListOfServers(AggregationBucketServerBucket listOfTargetServerBucket) {
		this.listOfServers = listOfTargetServerBucket;
	}

    
}
