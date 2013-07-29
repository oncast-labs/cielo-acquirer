package br.com.oncast.acquirerpimp.acquirer.configuration;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import br.com.oncast.acquirerpimp.bean.establishment.Establishment;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class CieloAcquirerConfiguration {

	public static final String FILE_PATH_PARAM_NAME = "cielo.acquirer.conf.path";

	private final Properties properties = new Properties();

	private final String configurationFilePath;

	@Inject
	CieloAcquirerConfiguration(@Named(FILE_PATH_PARAM_NAME) String path) {
		this.configurationFilePath = path;
		try {
			properties.load(CieloAcquirerConfiguration.class.getResourceAsStream(configurationFilePath));
		} catch (final IOException e) {
			throw new RuntimeException("A configuration file [" + configurationFilePath + "] in the classpath is needed", e);
		}
	}

	public URI getUrl() {
		String url = loadProperty("cielo.ws.url");
		return URI.create(url);
	}

	public Establishment getEstablishment() {
		String number = loadProperty("cielo.establishment.number");
		String key = loadProperty("cielo.establishment.key");
		try {
			return new Establishment(Long.valueOf(number), key);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Your cielo establishment number should be nom empty and numbers only, check your property file in '" + configurationFilePath + "'");
		}
	}

	private String loadProperty(String propertyName) {
		String value = properties.getProperty(propertyName);
		if (value == null) throw new RuntimeException("The '" + propertyName + "' property is missing in the file '" + configurationFilePath + "'");
		return value;
	}

}
