package br.com.augusto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.augusto.models.Arquivos;
import br.com.augusto.models.Empresas;

public interface ArquivoRepository extends CrudRepository<Arquivos, Integer> {

	List<Arquivos> findByEmpresa(Empresas empresa);

}
