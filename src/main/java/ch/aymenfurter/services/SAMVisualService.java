package ch.aymenfurter.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aymenfurter.elk.ELKClient;
import ch.aymenfurter.elk.model.AggregationBucket;
import ch.aymenfurter.elk.model.AggregationBucketServerBucketEntry;
import ch.aymenfurter.elk.model.ResponseContainer;
import ch.aymenfurter.model.SAMNode;
import ch.aymenfurter.model.SAMNodeConnection;
import ch.aymenfurter.model.SAMNodeConnectionResult;
import ch.aymenfurter.model.SAMNodeResult;

@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class SAMVisualService {
    private static final Logger LOG = LoggerFactory.getLogger(SAMVisualService.class);

    public SAMVisualService() {
    }

    
    @GET
    @javax.ws.rs.Path("/nodes/")
    public SAMNodeResult getSAMNodes() {    	
    	// FIXME: Multiple Requests    	
    	ResponseContainer container = ELKClient.getEntries();
    	Set<String> nodesSet = new HashSet<String>();
    	
    	for (AggregationBucket bucket : container.getAggregation().getAggregationEntries().getBuckets()) {
    		for (AggregationBucketServerBucketEntry entry: bucket.getListOfServers().getEntries()) { 
    			nodesSet.add(entry.getSourceServer());
    		}
    	}
    	
    	List<SAMNode> nodes = new ArrayList<SAMNode>();

    	for (String nodeName : nodesSet) {
    		SAMNode node = new SAMNode();
        	node.setName(nodeName);
        	nodes.add(node);
    	}
    	
    	return new SAMNodeResult(nodes);
    }

    @GET
    @javax.ws.rs.Path("/nodeconnections/")
    public SAMNodeConnectionResult getNodeConnections() {
    	List<SAMNodeConnection> nodeConnection = new ArrayList<SAMNodeConnection>();
    	HashMap<String, SAMNodeConnection> nodeConnections = new HashMap<String, SAMNodeConnection>();
    	ResponseContainer container = ELKClient.getEntries();

    	for (AggregationBucket bucket : container.getAggregation().getAggregationEntries().getBuckets()) {
    		String sourceLinkBucket = "";
    		for (AggregationBucketServerBucketEntry entry: bucket.getListOfServers().getEntries()) {
    			
    			if (!sourceLinkBucket.equals("")) {
    				SAMNodeConnection nodeCon = new SAMNodeConnection();    				
    		    	nodeCon.setSourceGraphName(sourceLinkBucket);
    		    	nodeCon.setTargetGraphName(entry.getSourceServer());    	
    		    	nodeCon.setWeight(1);
    		    	
    		    	if (nodeConnections.get(sourceLinkBucket + "-" + entry.getSourceServer()) != null) {
    		    		SAMNodeConnection con = nodeConnections.get(sourceLinkBucket + "-" + entry.getSourceServer());
    		    		con.setWeight(con.getWeight() + 1); 
    		    		nodeConnections.put(sourceLinkBucket + "-" + entry.getSourceServer(), con);
    		    	} else {
    		    		nodeConnections.put(sourceLinkBucket + "-" + entry.getSourceServer(), nodeCon);
    		    	}
    			} 
    			
    			sourceLinkBucket = entry.getSourceServer(); 
    		}
    	}
    	
    	nodeConnection.addAll(nodeConnections.values());    	
    	return new SAMNodeConnectionResult(nodeConnection);
    }
    

}
