package io.amecodelabs.ims.service;

import java.net.URISyntaxException;
import java.util.function.Consumer;

import io.amecodelabs.ims.broker.client.HttpConnect;
import io.amecodelabs.ims.broker.impl.client.HttpBroker;
import io.amecodelabs.ims.broker.impl.client.HttpPostRequest;
import io.amecodelabs.ims.models.utils.ContentValues;
import io.amecodelabs.ims.models.utils.JSONExportable;
import io.amecodelabs.ims.models.utils.JSONImportException;

public class AuthenticationService implements AbstractService {
	private final String LOCATION_URI = "http://localhost/auth";
	private Consumer<ContentValues> success;
	private Consumer<String> fail;
	
	public AuthenticationService(Consumer<ContentValues> success, Consumer<String> fail) {
		this.success = success;
		this.fail = fail;
	}
	
	public void authenticate(JSONExportable user) {
		HttpConnect httpConnect = HttpBroker.getHttpConnect();
		httpConnect
			.setErrorHandler( (err) -> fail.accept(err.getMessage()) )
			.setSuccessHandler( (res) -> {
				try {
					success.accept(ContentValues.newInstanceOfImportJSON("response", res.getBody()));
				} catch (JSONImportException e) {
					fail.accept(e.getMessage());
				}
			});
		
		HttpPostRequest request = null;
		try {
			
			request = new HttpPostRequest(LOCATION_URI);
			request.addHeader("User-Agent", USER_AGENT);
			request.addHeader("Accept", ACCEPT);
			request.addHeader("Accept-Charset", ACCEPT_CHARSET);
			request.addHeader("Accept-Language", ACCEPT_LANGUAGE);
			
			request.setContent(user.exportJSON(), "application/json");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		httpConnect.execute(request);
	}
	
}
