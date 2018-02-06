package br.senai.sp.jogos.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.senai.sp.jogos.model.Jogo;

@Repository
public class JogoDao {

	private static List<Jogo> jogos;
	{

		jogos = new ArrayList<Jogo>();

		jogos.add(new Jogo(1L, "God of War", "Sony"));
		jogos.add(new Jogo(2L, "Fifa", "EA"));
		jogos.add(new Jogo(3L, "WOW", "Blizzard"));

	}

	/**
	 * 
	 * @return lista de jogos
	 */
	public List<Jogo> list() {
		return jogos;
	}

	/**
	 * Retorna o objeto Jogo.
	 * 
	 * @param id
	 * @return Jogo
	 */
	public Jogo find(Long id) {
		for (Jogo jogo : jogos) {
			if (jogo.getId() == id) {
				return jogo;
			}
		}
		return null;
	}

	/**
	 * Salva o objeto Jogo.
	 * 
	 * @param j
	 * @return Jogo
	 */
	public Jogo save(Jogo j) {
		j.setId((long) (jogos.size() + 1));
		jogos.add(j);
		return j;
	}

	/**
	 * Remove o objeto Jogo.
	 * 
	 * @param id
	 * @return Jogo
	 */
	public Jogo remove(Long id) {
		for (Jogo jogo : jogos) {
			if (jogo.getId() == id) {
				jogos.remove(jogo);
				return jogo;
			}
		}
		return null;
	}

	/**
	 * Atualizar o objeto Jogo.
	 * 
	 * @param id
	 * @return Jogo
	 */
	public Jogo merge(Long id, Jogo j) {
		for (Jogo jogo : jogos) {
			if (jogo.getId() == id) {
				j.setId(jogo.getId());
				jogos.remove(jogo);
				jogos.add(j);
				return jogo;
			}
		}
		return null;

	}
	
	/**
	 * 
	 * Atualizar o objeto Jogo passando como parametro o proprio objeto
	 * 
	 * @param Jogo 
	 * @return Jogo
	 * 
	 * */
	public Jogo update(Jogo j) {
		for (Jogo jogo : jogos) {
			if (jogo.getId() == j.getId()) {
				j.setId(jogo.getId());
				jogos.remove(jogo);
				jogos.add(j);
				return jogo;
			}
		}
		return null;
		
	}

}
