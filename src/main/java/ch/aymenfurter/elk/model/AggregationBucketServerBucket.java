package ch.aymenfurter.elk.model;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregationBucketServerBucket {
    @JsonProperty(value = "buckets")
	private List<AggregationBucketServerBucketEntry> entries;

	public List<AggregationBucketServerBucketEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<AggregationBucketServerBucketEntry> entries) {
		this.entries = entries;
	}
}
