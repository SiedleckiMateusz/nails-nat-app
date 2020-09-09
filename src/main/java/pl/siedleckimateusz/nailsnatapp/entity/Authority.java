package pl.siedleckimateusz.nailsnatapp.entity;

import org.springframework.security.core.GrantedAuthority;


public enum Authority implements GrantedAuthority {
    CLIENT,EMPLOYEE,ADMIN;


    @Override
    public String getAuthority() {
        return this.name();
    }
}
