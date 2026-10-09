package jp.co.evo.common;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.util.Objects;

import ga.service.log.LogLevel;
import ga.service.log.LogService;

public class HttpService {
	private static HttpService instance = null;

	private HttpClient client = null;
	private String url = null;
	private LogService log = new LogService(HttpService.class);

	private HttpService() {
	}

	public static HttpService factory() {
		if (Objects.isNull(instance))
			instance = new HttpService();
		return instance;
	}

	public void initialize(String url) {
		this.client = HttpClient.newHttpClient();
		this.url = "http://" + url;
	}

	public HttpResponse<String> sendPost(String endpoint, String data) throws IOException, InterruptedException {
		log.print(LogLevel.DEBUG, "POST送信");
		log.print(LogLevel.DEBUG, " URL:" + url + "/" + endpoint);
		log.print(LogLevel.DEBUG, " DATA:" + url + "/" + endpoint);
		Builder builder = HttpRequest.newBuilder().uri(URI.create(url + "/" + endpoint));
		HttpRequest request = builder.POST(HttpRequest.BodyPublishers.ofString(data)).build();
		return this.client.send(request, HttpResponse.BodyHandlers.ofString());

	}
}
