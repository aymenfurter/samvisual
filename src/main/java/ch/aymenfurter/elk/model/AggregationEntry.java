package ch.aymenfurter.elk.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregationEntry {
	@JsonProperty(value = "buckets")
    private List<AggregationBucket> buckets;

	public List<AggregationBucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<AggregationBucket> buckets) {
		this.buckets = buckets;
	}
}
