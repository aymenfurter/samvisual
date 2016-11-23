package ch.aymenfurter.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    	List<SAMNode> nodes = new ArrayList<SAMNode>();
    	
    	SAMNode node = new SAMNode();
    	node.setName("test");
    	
    	SAMNode node2 = new SAMNode();
    	node2.setName("test2");
    	
    	nodes.add(node);
    	nodes.add(node2); 
    	
    	return new SAMNodeResult(nodes);
    }

    @GET
    @javax.ws.rs.Path("/nodeconnections/")
    public SAMNodeConnectionResult getNodeConnections() {
    	List<SAMNodeConnection> nodeConnection = new ArrayList<SAMNodeConnection>();
    	
    	SAMNodeConnection nodeCon = new SAMNodeConnection();
    	nodeCon.setSourceGraphName("test");
    	nodeCon.setTargetGraphName("test2");
    	
    	nodeConnection.add(nodeCon);
    	return new SAMNodeConnectionResult(nodeConnection);
    }
    

}
