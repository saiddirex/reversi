package org.sid.security;

import org.sid.data.JoueurRepository;
import org.sid.entities.Joueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired 
	JoueurRepository userRepository;
	@Override
	public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
		Joueur user=userRepository.findByUsername(username);
		if(null == user){
			throw new UsernameNotFoundException("No usernamed"+username);
			}else{
				return new UserDetailsImpl(user);
				}
		}
}
