package com.crudjava.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudjava.model.Estabelecimento;
import com.crudjava.repository.EstabelecimentoRespository;
import com.crudjava.util.ObjectUtil;

@Service
public class EstabelecimentoService {

	@Autowired
	private EstabelecimentoRespository repository;

	public List<Estabelecimento> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Estabelecimento findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional
	public Estabelecimento update(Estabelecimento estabelecimento) {
		Estabelecimento estabelecimentoDB = findById(estabelecimento.getId());

		ObjectUtil.copyNonNullProperties(estabelecimento, estabelecimentoDB);
		return repository.save(estabelecimentoDB);
	}

	@Transactional
	public Estabelecimento save(Estabelecimento estabelecimento) {
		return repository.save(estabelecimento);
	}

	@Transactional
	public void delete(Estabelecimento estabelecimento) {
		repository.delete(estabelecimento);
	}

}
