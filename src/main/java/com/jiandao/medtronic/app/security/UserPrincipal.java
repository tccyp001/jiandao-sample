package com.jiandao.medtronic.app.security;

import com.jiandao.medtronic.app.model.User;
import com.jiandao.medtronic.app.util.JiandaoBeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserPrincipal extends User implements UserDetails {

  private Collection<? extends GrantedAuthority> authorities;

  public static UserPrincipal create(User user){
    UserPrincipal userPrincipal = new UserPrincipal();

    JiandaoBeanUtils.copyPropertiesIgnoreNull(user, userPrincipal, com.jiandao.medtronic.app.model.UserDetails.class);
    JiandaoBeanUtils.copyPropertiesIgnoreNull(user, userPrincipal, User.class);

    return userPrincipal;
  }

  @Override
  public String getUsername() {
    return super.getFirstName();
  }

  @Override
  public String getPassword() {
    return  super.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(super.getId(), that.getId());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId());
  }
}
