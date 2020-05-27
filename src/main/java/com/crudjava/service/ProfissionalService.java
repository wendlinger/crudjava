package com.crudjava.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudjava.exception.BusinessException;
import com.crudjava.model.Estabelecimento;
import com.crudjava.model.Profissional;
import com.crudjava.repository.ProfissionalRespository;
import com.crudjava.util.ObjectUtil;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRespository repository;

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	public List<Profissional> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Profissional findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional
	public Profissional update(Profissional profissional) throws BusinessException {
		Profissional profissionalDB = findById(profissional.getId());

		ObjectUtil.copyNonNullProperties(profissional, profissionalDB);
		return repository.save(profissionalDB);
	}

	@Transactional
	public Profissional save(Profissional profissional) {
		return repository.save(profissional);
	}

	@Transactional
	public void delete(Profissional profissional) throws BusinessException {
		repository.delete(profissional);
	}

	public Profissional vincularEstabelecimento(Long estabelecimentoId, Long profissionalId) throws BusinessException {

		Profissional profissionalDB = findById(profissionalId);
		Estabelecimento estabelecimentoDB = estabelecimentoService.findById(estabelecimentoId);

		if (!(profissionalDB == null && estabelecimentoDB == null)) {
			if (profissionalDB.getEstabelecimento() != null) {
				throw new BusinessException("Profissional já vinculado ao estabelecimento "
						+ profissionalDB.getEstabelecimento().getNome() + "!");
			} else {
				profissionalDB.setEstabelecimento(estabelecimentoDB);
				return update(profissionalDB);
			}
		} else {
			throw new BusinessException("Estabelecimento ou Profissional não encontrados.");
		}

	}
}
