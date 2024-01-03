package ferchulobo777.um.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String FirstName;
    private String LastName;
    private LocalDate birthDate;
    private String username;
    private String email;
    private String password;
    private String gender;
    private String avatar;
    //usamos fetchType en EAGER para que cada vez que se acceda o que se extraiga un usuario de la BD, esto se traiga todos sus roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /*Con JoinTable estaremos creando una tabla pivote que unira la tabla usuario y role con lo cual tendremos un total de 3 tablas
    relacionadas en la tabla "usuarios_roles" a traves de las columnas usuario_id que apuntara al ID de la tabla usuario y role_id que apuntara al ID de la tabla role*/
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))
    private List<Role> roles = new ArrayList<>();
}
