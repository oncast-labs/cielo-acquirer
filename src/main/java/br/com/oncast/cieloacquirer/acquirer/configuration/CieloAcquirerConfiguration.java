package br.com.oncast.cieloacquirer.acquirer.configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import br.com.oncast.cieloacquirer.bean.establishment.Establishment;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class CieloAcquirerConfiguration {

	public static final String PROPERTIES_PATH_PARAM_NAME = "cielo.acquirer.conf.path";

	private static final String CIELO_ESTABLISHMENT_KEY = "cielo.establishment.key";

	private static final String CIELO_ESTABLISHMENT_NUMBER = "cielo.establishment.number";

	private static final String CIELO_WS_URL = "cielo.ws.url";

	private static final String ESTABLISHMENT_RETURN_URL = "establishment.returnUrl";

	private final Properties properties = new Properties();

	private final String configurationFilePath;

	@Inject
	CieloAcquirerConfiguration(@Named(PROPERTIES_PATH_PARAM_NAME) final String path) {
		this.configurationFilePath = path;
		try {
			properties.load(CieloAcquirerConfiguration.class.getResourceAsStream(configurationFilePath));
		} catch (final Exception e) {
			throw new RuntimeException("Missing configuration file[" + configurationFilePath + "] in the classpath with read permission", e);
		}
	}

	public URL getWebServiceUrl() {
		return loadUrlProperty(CIELO_WS_URL);
	}

	public Establishment getEstablishment() {
		final String number = loadProperty(CIELO_ESTABLISHMENT_NUMBER);
		final String key = loadProperty(CIELO_ESTABLISHMENT_KEY);
		try {
			return new Establishment(Long.valueOf(number), key);
		} catch (final NumberFormatException e) {
			throw new RuntimeException("Your cielo establishment number should be nom empty and numbers only, check your property file in '" + configurationFilePath + "'", e);
		}
	}

	public URL getReturnUrl() {
		return loadUrlProperty(ESTABLISHMENT_RETURN_URL);
	}

	private URL loadUrlProperty(final String propertyName) {
		final String url = loadProperty(propertyName);
		try {
			return new URL(url);
		} catch (final MalformedURLException e) {
			throw new RuntimeException("The '" + propertyName + "' property must be a valid url", e);
		}
	}

	private String loadProperty(final String propertyName) {
		final String value = properties.getProperty(propertyName);
		if (value == null) throw new RuntimeException("The '" + propertyName + "' property is missing in the file '" + configurationFilePath + "'");
		return value;
	}

}
