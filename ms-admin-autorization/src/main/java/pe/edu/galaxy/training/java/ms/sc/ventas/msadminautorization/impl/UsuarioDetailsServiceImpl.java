package pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization.entity.Usuario;
import pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization.repository.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.loadUserByUsername(username);
System.out.println("username : " + username);
		if (usuario == null) {
			System.out.println("NO");
			throw new UsernameNotFoundException(username);
		}
		UserBuilder  builder=null;
		builder= User.withUsername(usuario.getUsuario());
       // builder.password(new BCryptPasswordEncoder().encode(usuario.getClave()));
        builder.password(usuario.getClave());
        builder.roles("ADMIN");
		/*
		List<GrantedAuthority> lst = new ArrayList<>();
		//SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		lst.add(new SimpleGrantedAuthority("ADMIN"));
		User user = new User(usuario.getUsuario(), usuario.getClave(),lst);
		//User user = new User(usuario.getUsuario(), usuario.getClave(), emptyList());
		 * return user;
		*/
		return builder.build();

	}

}