package me.digi.verttests;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
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
		vertx.deployVerticle(ApplikationVerticle.class.getName(), context.asyncAssertSuccess());
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void testMyStaticIndexPage(TestContext context) {
		final Async async = context.async();
		List<String> responseList = new ArrayList<String>();
		vertx.createHttpClient().getNow(8081, "localhost", "/", response -> {
			response.handler(body -> {
				String responseString = body.toString();
				responseList.add(responseString);
				async.complete();
			});
			response.endHandler(body -> {
				String responseString = String.join("", responseList);
				context.assertTrue(responseString.contains("Mirko Eberlein")
						&& responseString.contains("Currency - Währungsrechner"));				
			});
		});
	}
	
	@Test
	public void testGetCurrencys(TestContext context) {
		final Async async = context.async();
		List<String> responseList = new ArrayList<String>();
		vertx.createHttpClient().getNow(8081, "localhost", "/getCurrencys/", response -> {
			response.handler(body -> {
				String responseString = body.toString();
				responseList.add(responseString);
				async.complete();
			});
			response.endHandler(body -> {
				String responseString = String.join("", responseList);
				JsonArray testConn = new JsonArray(responseString);
				context.assertTrue(testConn.size() == 32);
			});
		});
	}
	
	@Test
	public void calculate(TestContext context) {
		final Async async = context.async();
		vertx.createHttpClient().getNow(8081, "localhost", "/calculate/?from=EUR&to=CHF&value=1", response -> {
			response.handler(body -> {
				String responseString = body.toString();
				JsonObject json = new JsonObject(responseString);
				context.assertTrue(json.getDouble("result")>1);
				async.complete();
			});
		});
	}
	
	
}
