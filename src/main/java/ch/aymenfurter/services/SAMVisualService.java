package ch.aymenfurter.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aymenfurter.model.Node;
import ch.aymenfurter.model.NodeConnection;
import ch.aymenfurter.model.NodeConnectionResult;
import ch.aymenfurter.model.NodeResult;

@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class SAMVisualService {
    private static final Logger LOG = LoggerFactory.getLogger(SAMVisualService.class);

    public SAMVisualService() {
    }

    @GET
    @javax.ws.rs.Path("/nodes/")
    public Response getNodes() {
    	List<Node> nodes = new ArrayList<Node>();
    	
    	Node node = new Node();
    	node.setName("test");
    	
    	Node node2 = new Node();
    	node2.setName("test2");
    	
    	nodes.add(node);
    	nodes.add(node2); 
    	
    	System.out.println("nodes called" + nodes.size());
    	NodeResult result = new NodeResult(nodes);
    	Response resp = Response.ok(result).build();
    	System.out.println(resp.toString());
    	return Response.ok(new NodeResult(nodes)).build();
    }

    @GET
    @javax.ws.rs.Path("/nodeconnections/")
    public Response getNodeConnections() {
    	List<NodeConnection> nodeConnection = new ArrayList<NodeConnection>();
    	
    	NodeConnection nodeCon = new NodeConnection();
    	nodeCon.setSourceGraphName("test");
    	nodeCon.setTargetGraphName("test2");
    	
    	nodeConnection.add(nodeCon);
    	return Response.ok(new NodeConnectionResult(nodeConnection)).build();
    }
    

}
