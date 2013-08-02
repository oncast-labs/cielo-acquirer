package br.com.oncast.cieloacquirer;

import br.com.oncast.cieloacquirer.acquirer.configuration.CieloAcquirerConfiguration;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

public class CieloAcquirerModule extends AbstractModule {

	private static Injector injector;

	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named(CieloAcquirerConfiguration.PROPERTIES_PATH_PARAM_NAME)).toInstance("/acquirer.properties");
	}

	public static Injector getInjector() {
		if (injector == null) injector = Guice.createInjector(new CieloAcquirerModule());
		return injector;
	}

}
