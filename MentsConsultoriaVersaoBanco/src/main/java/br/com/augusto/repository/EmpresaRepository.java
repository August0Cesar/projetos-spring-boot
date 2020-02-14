package br.com.augusto.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.augusto.models.Empresas;

public interface EmpresaRepository extends CrudRepository<Empresas, Integer> {


}
