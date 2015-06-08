package com.remmelt.consul_config_provider.configuration;

import com.ecwid.consul.v1.ConsulClient;
import io.dropwizard.configuration.UndefinedEnvironmentVariableException;
import org.apache.commons.lang3.text.StrLookup;

public class ConsulKVLookup extends StrLookup<Object> {
	private final boolean strict;
	private static final ConsulClient client = new ConsulClient(System.getenv("CONSUL_HOST"));

	/**
	 * Create a new instance with strict behavior.
	 */
	public ConsulKVLookup() {
		this(true);
	}

	/**
	 * Create a new instance.
	 *
	 * @param strict {@code true} if looking up undefined environment variables should throw a
	 *               {@link UndefinedEnvironmentVariableException}, {@code false} otherwise.
	 * @throws UndefinedEnvironmentVariableException if the environment variable doesn't exist and strict behavior
	 *                                               is enabled.
	 */
	public ConsulKVLookup(boolean strict) {
		this.strict = strict;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws UndefinedConsulKVException if the variable doesn't exist in the KV store and strict behavior is enabled.
	 */
	@Override
	public String lookup(String key) {
		try {
			return new String(client.getKVBinaryValue(key).getValue().getValue());
		} catch (NullPointerException e) {
			if (strict) {
				throw new UndefinedConsulKVException(
						String.format("The variable with key '%s' is not found in the Consul KV store; "
								+ "could not substitute the expression '${%s}'.", key, key)
				);
			} else {
				return null;
			}
		}
	}
}
