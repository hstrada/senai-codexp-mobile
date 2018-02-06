package br.senai.sp.jogos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jogos.dao.JogoDao;
import br.senai.sp.jogos.model.Jogo;

@RestController
@RequestMapping("/")
public class JogosController {

	@Autowired
	private JogoDao jogoDao;

	// "/jogos"
	@GetMapping(value = "jogos")
	public ResponseEntity<List<Jogo>> list() {
		List<Jogo> jogos = jogoDao.list();
		if (jogos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Jogo>>(jogos, HttpStatus.OK);
	}

	// "/jogos/{id}"
	@GetMapping(value = "jogos/{id}")
	public ResponseEntity<Jogo> find(@PathVariable("id") Long id) {
		Jogo jogo = jogoDao.find(id);
		if (jogo == null)
			return new ResponseEntity<Jogo>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Jogo>(jogo, HttpStatus.OK);

	}

	// "/jogos"
	@PostMapping("/jogos")
	public ResponseEntity<Jogo> save(@RequestBody Jogo jogo) {

		Jogo j = new Jogo();

		j.setNome(jogo.getNome());
		j.setFabricante(jogo.getFabricante());

		jogoDao.save(j);

		return new ResponseEntity<Jogo>(j, HttpStatus.OK);
	}

	// "/jogos/{id}"
	@DeleteMapping(value = "/jogos/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		Jogo jogo = jogoDao.remove(id);

		if (jogo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	// "/jogos/{id}"
	@PutMapping("/jogos/{id}")
	public ResponseEntity<?> merge(@PathVariable("id") Long id, @RequestBody Jogo jogo) {

		Jogo j = jogoDao.find(id);

		if (j == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		j.setNome(jogo.getNome());
		j.setFabricante(jogo.getFabricante());

		jogoDao.merge(id, j);

		return new ResponseEntity<Jogo>(j, HttpStatus.OK);
	}

	@PutMapping("/jogos")
	public ResponseEntity<?> update(@RequestBody Jogo jogo) {
		Jogo j = jogoDao.find(jogo.getId());

		if (j == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		j.setNome(jogo.getNome());
		j.setFabricante(jogo.getFabricante());

		jogoDao.update(j);
		
		return new ResponseEntity<Jogo>(j, HttpStatus.OK);
	}

}
