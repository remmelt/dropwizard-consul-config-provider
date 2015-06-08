package com.remmelt.consul_config_provider.configuration;

public class UndefinedConsulKVException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UndefinedConsulKVException(String errorMessage) {
		super(errorMessage);
	}
}
