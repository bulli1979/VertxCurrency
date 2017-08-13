package me.digi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class ApplikationVerticle extends AbstractVerticle {
	HttpServer http;

	@Override
	public void start(Future<Void> future) throws Exception {
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		
		router.get("/getCurrencys/").handler(rc -> {
			vertx.executeBlocking(block -> {
				block.complete(ServiceClass.getAllCurrencys());
			}, res -> {
				rc.response().putHeader("content-type", "application/json").end(res.result().toString());
			});
		});

		router.get("/calculate/").handler(rc -> {
			vertx.executeBlocking(block -> {
				System.out.println(rc.request().getParam("from"));
				System.out.println(rc.request().getParam("to"));
				System.out.println(rc.request().getParam("amount"));
				block.complete(ServiceClass.calculateCourse(rc.request().getParam("from"), rc.request().getParam("to"),
						rc.request().getParam("amount")));
			}, res -> {
				rc.response().putHeader("content-type", "application/json").end(res.result().toString());
			});
		});

		router.route().handler(StaticHandler.create());
		http = vertx.createHttpServer()
				.requestHandler(router::accept)
				.listen(config().getInteger("http.port", 8081),
				result -> {
					if (result.succeeded()) {
						future.complete();
					} else {
						future.fail(result.cause());
					}
				});
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		http.close(closed -> {
			if (closed.succeeded()) {
				stopFuture.complete();
			} else {
				stopFuture.fail(closed.cause());
			}
		});
	}
}
