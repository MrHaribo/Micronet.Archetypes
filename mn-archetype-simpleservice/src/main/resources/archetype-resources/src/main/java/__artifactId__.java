#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import micronet.annotation.MessageListener;
import micronet.annotation.MessageService;
import micronet.annotation.OnStart;
import micronet.network.Context;
import micronet.network.Request;

@MessageService(uri = "mn://${artifactId.toLowerCase()}")
public class ${artifactId} {
	
	@OnStart
	public void onStart(Context context) {
		System.out.println("${artifactId} Start Routine...");
		context.sendRequest("mn://${artifactId.toLowerCase()}/hello/world/handler", new Request("Hello"));
	}
	
	@MessageListener(uri="/hello/world/handler")
	public void helloHandler(Context context, Request request) {
		System.out.println(request.getData() + " World MicroNet...");
	}
}

