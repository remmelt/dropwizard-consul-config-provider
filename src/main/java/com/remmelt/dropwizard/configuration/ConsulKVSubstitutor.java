package com.remmelt.dropwizard.configuration;

import org.apache.commons.lang3.text.StrSubstitutor;

public class ConsulKVSubstitutor extends StrSubstitutor {
	public ConsulKVSubstitutor() {
		this(true, false);
	}

	public ConsulKVSubstitutor(boolean strict) {
		this(strict, false);
	}

	/**
	 * @param strict                  {@code true} if looking up undefined environment variables should throw a
	 *                                {@link UndefinedConsulKVException}, {@code false} otherwise.
	 * @param substitutionInVariables a flag whether substitution is done in variable names.
	 */
	public ConsulKVSubstitutor(boolean strict, boolean substitutionInVariables) {
		super(new ConsulKVLookup(strict));

		this.setEnableSubstitutionInVariables(substitutionInVariables);
	}
}

