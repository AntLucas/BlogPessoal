package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Tema; //importando o model tema
import org.generation.blogPessoal.repository.TemaRepository; //importando o repositorio TemaRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // conseguimos ver esse controller independente da origem
@RequestMapping("/temas")
public class TemaController {

	@Autowired
	private TemaRepository repository; //injetando dependência

	@GetMapping("/todos") //buscar todos
	public ResponseEntity<List<Tema>> getAll() {

		return ResponseEntity.ok(repository.findAll());

	}
	
	@GetMapping("/id/{id}") //buscar pelo id
	public ResponseEntity<Tema> getById(@PathVariable long id) {

		return repository.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping("/nome/{nome}") //buscar pelo nome
	public ResponseEntity<List<Tema>> getByName(@PathVariable String nome) {

		return ResponseEntity.status(200).body(repository.findAllByDescricaoContainingIgnoreCase(nome));

	}
	
	
	@PostMapping("/cadastrar")//cadastrar tema
	public ResponseEntity<Tema> post(@RequestBody Tema tema){
		return ResponseEntity.status(201)
				.body(repository.save(tema));
	} 
	
	@PutMapping("/atualizar")//atualizar tema
	public ResponseEntity<Tema> put(@RequestBody Tema tema){
		return ResponseEntity.status(201)
				.body(repository.save(tema));
	} 
	
	@DeleteMapping("deletar/{id}") //deletar tema
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
