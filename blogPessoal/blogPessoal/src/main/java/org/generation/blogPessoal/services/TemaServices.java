package org.generation.blogPessoal.services;

import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaServices {

	//depencia com o repositório
	private @Autowired TemaRepository repository;
	
	/**
	 * Método para cadastrar tema
	 * @param novoTema
	 * @return  Optional com uma entidade Tema caso a descrição não tenha sido utilizado ou um Optional vazio caso a descrição já tenha sido utilizada
	 */
	public Optional<Tema> cadastrarTema (Tema novoTema){
		Optional<Tema> temaExistente = repository.findByDescricao(novoTema.getDescricao());
		
		if(!temaExistente.isPresent()) {
			return Optional.ofNullable(repository.save(novoTema));
		} else {
			return Optional.empty();
		}
		
	}
	
	
	public Optional<Tema> atualizarTema (Long id ,Tema atualizadoTema){
		Optional<Tema> temaExistente = repository.findById(id);
		
		if(temaExistente.isPresent()) {
			temaExistente.get().setDescricao(atualizadoTema.getDescricao());
			return Optional.ofNullable(repository.save(temaExistente.get()));
		} else {
			return Optional.empty();
		}
	}
}
