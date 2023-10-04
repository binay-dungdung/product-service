package dev.binaydungdung.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

	private String errorCode;
	private String errorMessage;
}
