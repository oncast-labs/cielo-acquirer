package br.com.oncast.cieloacquirer.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpPostSender {

	private final String url;
	private final List<NameValuePair> params;

	public HttpPostSender(final String url) {
		this.url = url;
		this.params = new ArrayList<NameValuePair>();
	}

	public HttpPostSender addParam(final String name, final String value) {
		params.add(new BasicNameValuePair(name, value));
		return this;
	}

	public String doPost() {
		try {
			final HttpClient client = new DefaultHttpClient();
			final HttpPost post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(params));
			return EntityUtils.toString(client.execute(post).getEntity());
		} catch (final IOException e) {
			throw new RuntimeException("Could not send POST request to " + url, e);
		}
	}

}
