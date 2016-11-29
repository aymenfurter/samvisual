package ch.aymenfurter.elk.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aggregation {
	@JsonProperty(value = "2")
    private AggregationEntry aggregationEntries;

	public AggregationEntry getAggregationEntries() {
		return aggregationEntries;
	}

	public void setAggregationEntries(AggregationEntry aggregationEntries) {
		this.aggregationEntries = aggregationEntries;
	}

}
