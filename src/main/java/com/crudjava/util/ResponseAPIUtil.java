package com.crudjava.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.crudjava.enums.SeverityEnum;
import com.crudjava.exception.BusinessException;

@Component
public class ResponseAPIUtil {

	@Value("${messages.default.registry.not-found}")
	public String messageNotFound;

	@Value("${messages.default.registry.saved}")
	public String messageSaved;

	@Value("${messages.default.registry.updated}")
	public String messageUpdated;

	@Value("${messages.default.registry.deleted}")
	public String messageDeleted;

	@Value("${messages.default.error.internal-error}")
	public String messageInternalError;

	public ResponseAPI getDefaultSaveResponse(Object object) {
		return mountResponse(Boolean.TRUE, object, Arrays.asList(new MessageAPI(messageSaved, SeverityEnum.SUCCESS)));
	}

	public ResponseAPI getDefaultUpdateResponse(Object object) {
		return mountResponse(Boolean.TRUE, object, Arrays.asList(new MessageAPI(messageUpdated, SeverityEnum.SUCCESS)));
	}

	public ResponseAPI getDefaultDeleteResponse() {
		return mountResponse(Boolean.TRUE, null, Arrays.asList(new MessageAPI(messageDeleted, SeverityEnum.SUCCESS)));
	}

	public ResponseAPI getDefaultValidadeResponse() {
		return mountResponse(Boolean.TRUE, null, null);
	}

	public ResponseAPI getDefaultInternalServerErrorResponse() {
		return mountResponse(Boolean.FALSE, Arrays.asList(new MessageAPI(messageInternalError, SeverityEnum.ERROR)));
	}

	public ResponseAPI getDefaultNotFoundResponse() {
		return mountResponse(Boolean.FALSE, Arrays.asList(new MessageAPI(messageNotFound, SeverityEnum.ERROR)));
	}

	public ResponseAPI getDefaultExceptionResponse(Exception e) {
		e.printStackTrace();

		if (e instanceof BusinessException) {
			return mountErrorResponse(e.getMessage());
		}

		String erroMsg = e.getMessage();
		Throwable cause = e;

		while (cause.getCause() != null) {
			cause = cause.getCause();
			erroMsg += cause.getMessage() + " ¬ ";
		}

		return mountErrorResponse(erroMsg);
	}

	public ResponseEntity<ResponseAPI> getDefaultResponseEntity(Object object) {
		return getDefaultResponseEntity(mountSuccessResponse(object));
	}

	public ResponseEntity<ResponseAPI> getDefaultResponseEntity(ResponseAPI responseAPI) {
		return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
	}

	public ResponseEntity<ResponseAPI> getDefaultResponseEntityExceptionError(Exception e) {
		return getDefaultResponseEntity(getDefaultExceptionResponse(e));
	}

	public ResponseAPI mountErrorResponse(String error) {
		return mountResponse(Boolean.FALSE, null, Arrays.asList(new MessageAPI(error, SeverityEnum.ERROR)));
	}

	public ResponseAPI mountErrorResponse(List<MessageAPI> listError) {
		return mountResponse(Boolean.FALSE, null, listError);
	}

	public ResponseAPI mountSuccessResponse(Object object) {
		return mountResponse(Boolean.TRUE, object, null);
	}

	public ResponseAPI mountResponse(boolean success) {
		return mountResponse(success, null, null);
	}

	public ResponseAPI mountResponse(Boolean success, List<MessageAPI> listMessage) {
		return mountResponse(success, null, listMessage);
	}

	public ResponseAPI mountResponse(Boolean success, Object object, List<MessageAPI> listMessage) {
		return new ResponseAPI(success, object, listMessage);
	}

}
