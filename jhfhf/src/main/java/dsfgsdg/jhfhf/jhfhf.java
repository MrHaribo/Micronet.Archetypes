package dsfgsdg.jhfhf;

import micronet.annotation.MessageListener;
import micronet.annotation.MessageService;
import micronet.annotation.OnStart;
import micronet.network.Context;
import micronet.network.Request;

@MessageService(uri = "mn://my_service")
public class jhfhf {
	
	@OnStart
	public void onStart(Context context) {
		System.out.println("jhfhf Started...");
		context.sendRequest("mn://my_service/hello/world/handler", new Request("Hello"));
	}
	
	@MessageListener(uri="/hello/world/handler")
	public void helloHandler(Context context, Request request) {
		System.out.println(request.getData() + " World MicroNet...");
	}
}

