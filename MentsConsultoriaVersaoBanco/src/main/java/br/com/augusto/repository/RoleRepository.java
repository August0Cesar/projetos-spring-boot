package br.com.augusto.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.augusto.models.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

	public Role findByNomeRole(String string);

}
