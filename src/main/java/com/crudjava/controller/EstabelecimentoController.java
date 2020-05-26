package com.crudjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudjava.model.Estabelecimento;
import com.crudjava.service.EstabelecimentoService;
import com.crudjava.util.ResponseAPI;
import com.crudjava.util.ResponseAPIUtil;

@RestController
@RequestMapping("estabelecimento")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService service;

	@Autowired
	protected ResponseAPIUtil responseAPIUtil;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseAPI> findAll() {
		try {
			return new ResponseEntity<ResponseAPI>(responseAPIUtil.mountSuccessResponse(service.findAll()),
					HttpStatus.OK);
		} catch (Exception e) {
			return responseAPIUtil.getDefaultResponseEntityExceptionError(e);
		}
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseAPI> findById(@PathVariable Long id) {
		try {
			Estabelecimento estabelecimentoDB = service.findById(id);

			if (estabelecimentoDB == null) {
				return new ResponseEntity<ResponseAPI>(responseAPIUtil.getDefaultNotFoundResponse(), HttpStatus.OK);
			}
			return new ResponseEntity<ResponseAPI>(responseAPIUtil.mountSuccessResponse(estabelecimentoDB),
					HttpStatus.OK);
		} catch (Exception e) {
			return responseAPIUtil.getDefaultResponseEntityExceptionError(e);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseAPI> save(@RequestBody Estabelecimento estabelecimento) {
		try {
			return new ResponseEntity<ResponseAPI>(responseAPIUtil.getDefaultSaveResponse(service.save(estabelecimento)), HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<ResponseAPI>(responseAPIUtil.mountErrorResponse(e.getRootCause().getLocalizedMessage()), HttpStatus.OK);
		} catch (Exception e) {
			return responseAPIUtil.getDefaultResponseEntityExceptionError(e);
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseAPI> update(@RequestBody Estabelecimento estabelecimento) {
		try {
			return new ResponseEntity<ResponseAPI>(responseAPIUtil.getDefaultUpdateResponse(service.update(estabelecimento)), HttpStatus.OK);
		} catch (Exception e) {
			return responseAPIUtil.getDefaultResponseEntityExceptionError(e);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseAPI> delete(@PathVariable Long id) {
		try {
			Estabelecimento estabelecimento = service.findById(id);
			if (estabelecimento == null) {
				return new ResponseEntity<ResponseAPI>(responseAPIUtil.getDefaultNotFoundResponse(), HttpStatus.OK);
			}
			service.delete(estabelecimento);
			return new ResponseEntity<ResponseAPI>(responseAPIUtil.getDefaultDeleteResponse(), HttpStatus.OK);
		} catch (Exception e) {
			return responseAPIUtil.getDefaultResponseEntityExceptionError(e);
		}
	}
	
	
}
