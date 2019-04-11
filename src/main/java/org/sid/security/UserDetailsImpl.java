package org.sid.security;
import java.util.Collection;

import org.sid.data.JoueurRepository;
import org.sid.entities.Joueur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
public class UserDetailsImpl implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4376618561628929842L;
	private Joueur joueur;
	private JoueurRepository joueurRepository;
	public  UserDetailsImpl(Joueur joueur){
		this.joueur = joueur;
		}

	
	@Override
	public boolean isAccountNonExpired() {
		return true;
		}
	@Override
	public boolean isAccountNonLocked() {
		return true;
		}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
		}
	@Override
	public boolean isEnabled() {
		return true;
		}
	@Override
	public String getUsername() {
		return joueur.getUsername();
		}
	@Override
	public String getPassword() {
		return joueur.getPassword();}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
