package com.santos.will.search.api;

import java.util.Collection;

/**
 * Define o contrato para todas as formas de busca no sistema
 * 
 * @author William
 * @since 2016-10-25
 * @version 1.0.0
 */
public interface Search<T> {

	/**
	 * Ação para executar a consulta na base de dados
	 * 
	 * @param query
	 * @return
	 */
	Collection<T> search(final String query);
}
