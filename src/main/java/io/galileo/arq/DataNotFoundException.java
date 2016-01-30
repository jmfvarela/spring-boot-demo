package io.galileo.arq;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException(String name) {
		super("could not find '" + name + "'.");
	}
}