package com.crudjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudjava.exception.BusinessException;
import com.crudjava.service.EstabelecimentoService;
import com.crudjava.service.ProfissionalService;
import com.crudjava.util.ResponseAPI;
import com.crudjava.util.ResponseAPIUtil;

@RestController
@RequestMapping(value = "/estabecimento/{estabelecimentoId}/profissional")
public class EstabelecimentoProfissionalController {

	@Autowired
	ProfissionalService profissionalService;

	@Autowired
	EstabelecimentoService estabelecimentoService;

	@Autowired
	protected ResponseAPIUtil responseAPIUtil;

	@PutMapping("/{profissionalId}")
	public ResponseEntity<ResponseAPI> associar(@PathVariable Long estabelecimentoId, @PathVariable Long profissionalId)
			throws BusinessException {
		try {
			return new ResponseEntity<ResponseAPI>(responseAPIUtil.getDefaultUpdateResponse(
							profissionalService.vincularEstabelecimento(estabelecimentoId, profissionalId)),
					HttpStatus.OK);
		} catch (Exception e) {
			return responseAPIUtil.getDefaultResponseEntityExceptionError(e);
		}
	}

}
