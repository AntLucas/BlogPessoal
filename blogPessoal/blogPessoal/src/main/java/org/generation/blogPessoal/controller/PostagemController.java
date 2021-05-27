package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.repository.PostagemRepository; //importando a interface
import org.generation.blogPessoal.model.Postagem; //importando a model

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController // indica que a classe é um controlador, um controller - se comunica com o
				// cliente
@RequestMapping("/postagens") // indica por on de a classe vai ser acessada
@CrossOrigin("*") // indica que a api aceita requisições de qualquer origem
public class PostagemController {

	// injetando a classe de repositório dentro do controller
	@Autowired // garante que todos os serviços da interface PostagemRepository seja acessado a
				// partir do controller
	private PostagemRepository repository;

	// metodo GetAll, recebe uma lista do tipo Postagem
	@GetMapping("/todos") //Sempre que houver uma requisição externa com o método GET através da url "/postagens" o metodo GetAll será executado
	//ResponseEntity retorna um status
	public ResponseEntity<List<Postagem>> GetAll(){
		//return ResponseEntity.ok(repository.findAll()); //retornar ok, com a requisição de todas as postagens
		//return ResponseEntity.status(202).body(repository.findAll()); // retorna um status 202
		List<Postagem> listaDePostagem = repository.findAll();
		//verifica se a lista está vazia ou não
		if(!listaDePostagem.isEmpty()) {
			//caso não seja vazia retorna um status 200 ok com a lista
			return ResponseEntity.status(HttpStatus.OK).body(listaDePostagem);
		}else{
			//caso a lista esteja vazia retorna um no content 204 vazio
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("id/{id}") // metodo para retornar valores através do id, o método recebe um parâmetro long
							// id
	// notação PathVariable para conseguir usar como parâmetro um valor vindo da url
	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		// assim que for feita uma reposição do tipo get em "/postagens/" e for passado
		// um valor no caso o "id"
		// esse método será executado que capturará a variável que está sendo passada e
		// vai retornar
		// a interface que foi injetada e executará o método findById que pode retornar
		// um método do tipo postagem com ok
		// quanto um notFound caso o objeto não exista ou tenha algum erro na requisição
	}

	@GetMapping("/titulo/{titulo}") // metodo para retornar valores através do titulo
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
		// metodo que sera executado quando acessarem a url "/titulo/" passando algum
		// parâmetro
		// dentro do metodo é executado o metodo que fizemos dentro da interface
		// repositorio findAllByTituloContainingIgnoreCase
	}
	
	//cadastro
	@PostMapping("/salvar")
	//request body pega algo do corpo do frontEnd que sera requisitado em um formato .json
	public ResponseEntity<Postagem>  salvarPostagem(@RequestBody Postagem novaPostagem) {
		
		//Optional<Postagem> postagemExistente = repository.findByPostagem(novaPostagem.getTitulo());
		//um optional que pesquisa caso tenha uma postagem com o título passado através do método criado na interface repository
		
		/*if(postagemExistente.isPresent()) {
		//verifica se a postagemExistente está presente
			//return postagemExistente.get();
			return null;
			//retorna o que ta dentro, no caso uma postagem
		} else {
			return repository.save(novaPostagem);
			//retornando um cadastro de postagem com o objeto json passado pelo body, já não exista um usuario com esse titulo
		}*/
		// return repository.save(novaPostagem);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(novaPostagem));
		
	}
	
	//atualizar 
	@PutMapping("/atualizar")
	public ResponseEntity<Postagem>  atualizaPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(200).body(repository.save(postagem));
	}
	
	//deletar
	@DeleteMapping("/deletar/{id}")
	public void deletaPostagem(@PathVariable long id) {
		repository.deleteById(id);
	}
}
