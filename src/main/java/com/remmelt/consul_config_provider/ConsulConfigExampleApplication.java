package com.remmelt.consul_config_provider;

import com.remmelt.consul_config_provider.resources.ExampleResource;
import com.remmelt.dropwizard.configuration.ConsulKVSubstitutor;
import io.dropwizard.Application;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ConsulConfigExampleApplication extends Application<ConsulConfigExampleConfiguration> {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			args = new String[]{"server", "src/main/resources/config.yml"};
		}
		new ConsulConfigExampleApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<ConsulConfigExampleConfiguration> bootstrap) {
		bootstrap.setConfigurationSourceProvider(
				new SubstitutingSourceProvider(
						bootstrap.getConfigurationSourceProvider(),
						new ConsulKVSubstitutor(false)
				)
		);
	}

	@Override
	public void run(ConsulConfigExampleConfiguration configuration, Environment environment) throws Exception {
		ExampleResource resource = new ExampleResource(
				configuration.getSomeSetting(),
				configuration.getSomeOtherSetting(),
				configuration.getSomeSettingThatDoesNotExist()
		);
		environment.jersey().register(resource);
	}
}
