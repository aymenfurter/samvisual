package ch.aymenfurter.test;


import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.transport.local.LocalConduit;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import ch.aymenfurter.model.SAMNodeResult;
import ch.aymenfurter.services.SAMVisualService;

/**
 * Test service with in-memory local transport.
 */
public class NodeServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(NodeServiceTest.class);
    private final static String ENDPOINT_ADDRESS = "local://cxf-demo";
    private static List<Object> providers = Lists.newArrayList();

    private static Server server;
    private WebClient client;

    @BeforeClass
    public static void initialize() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(SAMVisualService.class);
        // add custom providers if any
        providers.add(new MOXyJsonProvider());

        sf.setProviders(providers);

        sf.setResourceProvider(SAMVisualService.class,
                new SingletonResourceProvider(new SAMVisualService(), true));
        sf.setAddress(ENDPOINT_ADDRESS);

        server = sf.create();
    }

    @AfterClass
    public static void destroy() throws Exception {
        server.stop();
        server.destroy();
    }

    @Before
    public void setUp() throws Exception {
        client = WebClient.create(ENDPOINT_ADDRESS, providers);
        WebClient.getConfig(client).getRequestContext().put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }

    @Test
    public void testXmlType() throws Exception {
        client.accept(MediaType.APPLICATION_XML_TYPE);
        client.path("/nodes/");
        Response response = client.get(Response.class);

        LOG.info("Response is\n{}", response.getEntity().toString());

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getEntity().toString())
                .startsWith("<?xml");

        SAMNodeResult nodeResult = client.get(SAMNodeResult.class);
        assertThat(nodeResult.getNodes()).isNotEmpty();
    }

    @Test
    public void testJsonData() throws Exception {
        client.accept(MediaType.APPLICATION_JSON_TYPE);
        client.path("/nodes/");
        Response response = client.get(Response.class);

        LOG.info("Response is\n{}", response.getEntity().toString());
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        SAMNodeResult nodeResult = client.get(SAMNodeResult.class);
        assertThat(nodeResult.getNodes()).isNotEmpty();
    }
}
