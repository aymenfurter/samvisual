package ch.aymenfurter.elk.model;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregationBucketServerBucketEntry {
    @JsonProperty(value = "key")
    private String sourceServer;
    
    @JsonProperty(value = "doc_count")
    private String amountOfRequests;

	public String getSourceServer() {
		return sourceServer;
	}

	public void setSourceServer(String sourceServer) {
		this.sourceServer = sourceServer;
	}

	public String getAmountOfRequests() {
		return amountOfRequests;
	}

	public void setAmountOfRequests(String amountOfRequests) {
		this.amountOfRequests = amountOfRequests;
	}
}
