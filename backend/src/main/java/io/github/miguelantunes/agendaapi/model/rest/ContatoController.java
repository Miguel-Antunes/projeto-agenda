package io.github.miguelantunes.agendaapi.model.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import io.github.miguelantunes.agendaapi.model.entity.Contato;
import io.github.miguelantunes.agendaapi.model.repository.ContatoRepository;

@RestController
@RequestMapping("/api/contatos")
@CrossOrigin("http://localhost:4200")
public class ContatoController {
	
	@Autowired
	private ContatoRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contato inserirContato(@RequestBody Contato contato) {
		return repository.save(contato);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletarContato(@PathVariable Integer id){
		repository.findById(id).map((contato)->{
			repository.delete(contato);
			return Void.TYPE;		
		}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Contato> listarContatos(){
		return repository.findAll();
	}
	@PatchMapping("{id}/favorito")
	@ResponseStatus(HttpStatus.OK)
	public void favoritarContato(@PathVariable Integer id){
		 repository.findById(id).map(contato ->{
			 
			boolean favorito = contato.getFavorito() == Boolean.TRUE;
			contato.setFavorito(!favorito);
			repository.save(contato);
			return Void.TYPE;
		}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
//	@PatchMapping("{id}/favorito")
//	@ResponseStatus(HttpStatus.OK)
//	public void favoritarContato(@PathVariable Integer id, @RequestBody Boolean favorito) {
//		Optional<Contato> contato = repository.findById(id);
//		contato.ifPresent((contato) -> {
//			contato.setFavorito(favorito);
//			repository.save(contato);
//		});
//	}
	@PutMapping("{id}/foto")
	public byte[] adicionarFoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo) {
		Optional<Contato> contato = repository.findById(id);
		return contato.map((c)-> {
			try {
				InputStream is = arquivo.getInputStream();
				byte[] bytes = new byte [(int)arquivo.getSize()];
				IOUtils.readFully(is, bytes);
				c.setFoto(bytes);
				repository.save(c);
				is.close();
				return bytes;
			}
			catch(IOException e){
				return null;
			}
		}).orElse(null);
	}

}
