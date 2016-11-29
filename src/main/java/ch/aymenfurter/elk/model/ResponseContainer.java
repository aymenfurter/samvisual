package ch.aymenfurter.elk.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseContainer {
	public Aggregation getAggregation() {
		return aggregation;
	}

	public void setAggregation(Aggregation aggregation) {
		this.aggregation = aggregation;
	}

	@JsonProperty(value = "aggregations")
    private Aggregation aggregation;
	
	@JsonProperty(value = "took")
    private int took;

	public int getTook() {
		return took;
	}

	public void setTook(int took) {
		this.took = took;
	}
}
