package com.remmelt.consul_config_provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ConsulConfigExampleConfiguration extends Configuration {
	@JsonProperty
	@NotEmpty
	private String someSetting;

	@JsonProperty
	@NotEmpty
	private String someOtherSetting;

	@JsonProperty
	@NotEmpty
	private String someSettingThatDoesNotExist;

	public String getSomeSetting() {
		return someSetting;
	}

	public String getSomeOtherSetting() {
		return someOtherSetting;
	}

	public String getSomeSettingThatDoesNotExist() {
		return someSettingThatDoesNotExist;
	}
}
