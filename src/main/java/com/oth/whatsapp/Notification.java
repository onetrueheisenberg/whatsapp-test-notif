package com.oth.whatsapp;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Notification {
	
	@Value("${test.url}")
	String url;
	
	@RequestMapping(value = "/number/{number}/text/{text}", method = RequestMethod.GET)
	public String sendNotification(@PathVariable("number") final String number, @PathVariable("text") final String text) throws ClientProtocolException, IOException {
		final String uri = url + "?phone=" + number + "&text=" + text;
		HttpGet httpGet = new HttpGet(uri);
		HttpClient httpCLient = HttpClientBuilder.create().build();
		HttpResponse response = httpCLient.execute(httpGet);
		return response.getEntity().getContent().toString();
	}

}
