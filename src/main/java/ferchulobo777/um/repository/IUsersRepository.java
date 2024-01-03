package ferchulobo777.um.repository;

import ferchulobo777.um.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<User, Long> {
    //Metodo para poder buscar un usuario mediante su nombre
    Optional<User> findByUsername(String username);
    //Metodo para poder verificar si un usuario existe en nuestra base de datos
    Boolean existsByUsername(String username);
}
