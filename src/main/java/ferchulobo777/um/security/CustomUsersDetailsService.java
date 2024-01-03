package ferchulobo777.um.security;


import ferchulobo777.um.model.Role;
import ferchulobo777.um.model.User;
import ferchulobo777.um.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailsService implements UserDetailsService {
    private IUsersRepository usersRepo;

    @Autowired
    public CustomUsersDetailsService(IUsersRepository usersRepo) {
        this.usersRepo = usersRepo;
    }
    //Metodo para traernos una lista de autoridades por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    //Metodo para traernos un usuario con todos sus datos por medio de sus username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = usersRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario No Encontrado"));
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), mapToAuthorities(users.getRoles()));
    }
}
