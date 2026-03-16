package com.meru.inventory.exception;

import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryNotFoundException  extends RuntimeException{

	public InventoryNotFoundException(String message) {
		super(message);
	}
	@Override
	public void printStackTrace(PrintWriter s) {
		//super.printStackTrace(s);
	}
}
