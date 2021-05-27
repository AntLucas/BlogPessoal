package org.generation.blogPessoal.services;

import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // define que é um service
public class PostagemServices {
	
	
	private @Autowired PostagemServices repository;
	
	//public Optional<Postagem> cadastrarPostagem(Postagem novaPostagem);
	//offnullable para criar um optional
}
