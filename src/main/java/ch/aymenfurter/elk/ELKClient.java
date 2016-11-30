package ch.aymenfurter.elk;

import java.io.IOException;
import java.util.Collections;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import ch.aymenfurter.elk.model.ResponseContainer;

public class ELKClient {
	private static String USERNAME = "";
	private static String PWD = "";
	private static String ESHOST = "";
	private static String INDEXNAME = "";
	
	public static ResponseContainer getEntries() {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USERNAME, PWD));
		
		RestClient restClient = RestClient.builder(new HttpHost(ESHOST, 9200, "https")).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        }).build();
		
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
				"                   \"gte\": \"now-30d\"," +
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
				"            \"field\": \"service\"," +
				"            \"size\": 0," +
				"            \"order\": {" +
				"              \"_count\": \"desc\"" +
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
			        "/" + INDEXNAME + "/_search",
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
