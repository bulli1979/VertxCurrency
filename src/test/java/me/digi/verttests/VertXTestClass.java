package me.digi.verttests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import me.digi.ApplikationVerticle;

@RunWith(VertxUnitRunner.class)
public class VertXTestClass {
	private Vertx vertx;
	  @Before
	  public void setUp(TestContext context) {
	    vertx = Vertx.vertx();
	    vertx.deployVerticle(ApplikationVerticle.class.getName(),
	        context.asyncAssertSuccess());
	  }
	  @After
	  public void tearDown(TestContext context) {
	    vertx.close(context.asyncAssertSuccess());
	  }
	  @Test
	  public void testMystaticIndexPage(TestContext context) {
	    final Async async = context.async();
	    vertx.createHttpClient().getNow(8081, "localhost", "/",
	     response -> {
	      response.handler(body -> {
	    	String responseString = body.toString();
	        context.assertTrue(responseString.contains("Mirko Eberlein") || 
	        		responseString.contains("Currency - Währungsrechner"));
	        async.complete();
	      });
	    });
	  }
}
