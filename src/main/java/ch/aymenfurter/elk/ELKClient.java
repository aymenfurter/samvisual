package ch.aymenfurter.elk;

import java.io.IOException;
import java.util.Collections;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import ch.aymenfurter.elk.model.ResponseContainer;

public class ELKClient {
	public static ResponseContainer getEntries() {
		
		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
		
		Response resp = null;
		
		String jsonSearchQry = "{" +
				"  \"size\": 0," +
				"  \"query\": {" +
				"    \"filtered\": {" +
				"      \"query\": {" +
				"        \"query_string\": {" +
				"          \"query\": \"*\"," +
				"          \"analyze_wildcard\": true" +
				"        }" +
				"      }," +
				"      \"filter\": {" +
				"        \"bool\": {" +
				"          \"must\": [" +
				"            {" +
				" 		      \"range\": {" +
				"                \"@timestamp\": {" +
				"                   \"gte\": \"now-48h\"," +
				"            	   \"lte\": \"now\"" +
				"                }" +
				"              }" +
				"            }" +
				"          ]," +
				"          \"must_not\": []" +
				"        }" +
				"      }" +
				"    }" +
				"  }," +
				"  \"aggs\": {" +
				"    \"2\": {" +
				"      \"terms\": {" +
				"        \"field\": \"flowId\"," +
				"        \"size\": 0," +
				"        \"order\": {" +
				"          \"_count\": \"desc\"" +
				"        }" +
				"      }," +
				"      \"aggs\": {" +
				"        \"3\": {" +
				"          \"terms\": {" +
				"            \"field\": \"hostName\"," +
				"            \"size\": 0," +
				"            \"order\": {" +
				"              \"_term\": \"desc\"" +
				"            }" +
				"          }" +
				"        }" +
				"      }" +
				"    }" +
				"  }" +
				"}";
		
		
		HttpEntity entity = new NStringEntity(jsonSearchQry, ContentType.APPLICATION_JSON);
		
		try {
			resp = restClient.performRequest(
			        "GET",
			        "/sam/1/_search",
			        Collections.<String, String>emptyMap(),
			        entity);
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		ObjectMapper mapper = new ObjectMapper();
		ResponseContainer responseHits = null;
		try {
			responseHits = mapper.readValue(resp.getEntity().getContent(), ResponseContainer.class);
		} catch (UnsupportedOperationException | IOException e1) {
			e1.printStackTrace();
		}		 
		
		try {
			restClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseHits;


	}
}
