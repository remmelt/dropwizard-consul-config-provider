package com.remmelt.consul_config_provider.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {
	private String someSetting, someOtherSetting, someSettingThatDoesNotExist;

	public ExampleResource(String someSetting, String someOtherSetting, String someSettingThatDoesNotExist) {
		this.someSetting = someSetting;
		this.someOtherSetting = someOtherSetting;
		this.someSettingThatDoesNotExist = someSettingThatDoesNotExist;
	}

	@GET
	public String getExample() {
		return String.format("someSetting: %s\nsomeOtherSetting: %s\nsomeSettingThatDoesNotExist: %s",
				someSetting, someOtherSetting, someSettingThatDoesNotExist);
	}
}
