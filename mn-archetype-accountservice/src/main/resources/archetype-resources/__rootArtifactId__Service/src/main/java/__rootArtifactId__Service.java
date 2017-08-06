#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}Service.CredentialValues;
import ${package}Service.UserValues;
import micronet.annotation.MessageListener;
import micronet.annotation.MessageService;
import micronet.annotation.OnStart;
import micronet.annotation.OnStop;
import micronet.network.Context;
import micronet.network.Request;
import micronet.network.Response;
import micronet.network.StatusCode;
import micronet.serialization.Serialization;

@MessageService(uri="mn://account")  
public class ${artifactId} { 

	AccountDatabase database;
	
	@OnStart
	public void onStart(Context context) {
		database = new AccountDatabase();
	}
	
	@OnStop
	public void onStop(Context context) {  
		database.shutdown();
	}

	@MessageListener(uri="/register")
	public Response onRegister(Context context, Request request) {
		CredentialValues credentials = Serialization.deserialize(request.getData(), CredentialValues.class);
		UserValues existingUser = database.getUser(credentials.getUsername());

		if (existingUser != null) {
			System.out.println("User already registred! " + existingUser.getCredentials().getUsername());
			return new Response(StatusCode.CONFLICT, "User already registred...");
		} else {
			if (database.addUser(credentials)) {
				System.out.println("User added: " + credentials.getUsername()); 
				return new Response(StatusCode.OK, "User registred");
			} else {
				return new Response(StatusCode.INTERNAL_SERVER_ERROR, "Error registering User");
			}
		}
	}
	
	@MessageListener(uri="/login")
	public Response onLogin(Context context, Request request) {
		CredentialValues credentials = Serialization.deserialize(request.getData(), CredentialValues.class);
		UserValues user = database.getUser(credentials.getUsername());

		if (user == null)
			return new Response(StatusCode.NOT_FOUND);
		if (!credentials.getPassword().equals(user.getCredentials().getPassword()))
			return new Response(StatusCode.UNAUTHORIZED);
		
		return new Response(StatusCode.OK, Integer.toString(user.getId()));
	}
}

