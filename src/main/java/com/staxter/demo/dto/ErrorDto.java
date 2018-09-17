package com.staxter.demo.dto;

public class ErrorDto {

	private String code;
	private String description;

	public ErrorDto(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
