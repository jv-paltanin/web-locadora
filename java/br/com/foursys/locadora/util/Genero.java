package br.com.foursys.locadora.util;

/**
 * Enum responsável por armaazenar os gêneros de filmes
 * 
 * @author João Victor
 * @since 29/04/2021
 * @version 1.0
 */
public enum Genero {

	ACAO("Ação"), AVENTURA("Aventura"), COMEDIA("Comédia"), COMEDIAROMANTICA("Comédia Romântica"), DRAMA("Drama"),
	DOCUMENTARIO("Documentário"), ESPIONAGEM("Espionagem"), FAROESTE("Faroeste"), FANTASIA("Fantasia"),
	FICCAOCIENTIFICA("Ficção Científica"), GUERRA("Guerra"), MUSICAL("Musical"), POLICIAL("Policial"),
	ROMANCE("Romance"), SERIADO("Seriado"), SUSPENSE("Suspense"), TERROR("Terror");

	private String descricao;

	Genero(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
