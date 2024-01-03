package ferchulobo777.um.repository;


import ferchulobo777.um.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    //Metodo para buscar un Rol por su nombre en nuestra BD
    Optional<Role> findByName(String name);
}
