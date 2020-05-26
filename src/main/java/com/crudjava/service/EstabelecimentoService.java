package com.crudjava.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.crudjava.exception.BusinessException;
import com.crudjava.model.Estabelecimento;
import com.crudjava.model.Profissional;
import com.crudjava.repository.EstabelecimentoRespository;
import com.crudjava.util.ObjectUtil;

@Service
public class EstabelecimentoService {

	@Autowired
	private EstabelecimentoRespository repository;
	
	@Autowired
	private ProfissionalService profissionalService;

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
	
	public Estabelecimento vincularEstabelecimento(Long estabelecimentoId, Long profissionalId) throws BusinessException {

		StringBuilder errors = new StringBuilder();

		Profissional profissionalDB = profissionalService.findById(profissionalId);
		Estabelecimento estabelecimentoDB = findById(estabelecimentoId);

		if (!(profissionalDB == null && estabelecimentoDB == null)) {
			if (profissionalDB.getEstabelecimento() != null) {
				errors.append("Profissional já vinculado ao estabelecimento "
						+ profissionalDB.getEstabelecimento().getNome() + "!");
			} else {
				List<Profissional> listProfissional = estabelecimentoDB.getProfissionais();
				listProfissional.add(profissionalDB);
				estabelecimentoDB.setProfissionais(listProfissional);
				return update(estabelecimentoDB);
			}
		} else {
			errors.append("Estabelecimento ou Profissional não encontrados.");
			errors.append(System.getProperty("line.separator"));
		}

		if (!StringUtils.isEmpty(errors.toString())) {
			throw new BusinessException(errors.toString());
		}
		return null;
	}

}
