package pl.siedleckimateusz.nailsnatapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.UserToSession;

@Component
@SessionScope
public class AuthSuccessApplicationListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private final UserToSession user;

    private final static Logger logger = LoggerFactory.getLogger(AuthSuccessApplicationListener.class);

    public AuthSuccessApplicationListener(UserToSession user) {
        this.user = user;
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent interactiveAuthenticationSuccessEvent) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserEntity){
            UserEntity userEntity = (UserEntity)principal;
            setField(user,userEntity);
        }

        logger.info("Seting user in session: "+user.getFirstAndLastName());

    }

    private void setField(UserToSession user, UserEntity userEntity) {
        user.setId(userEntity.getId());
        user.setAuthority(userEntity.getAuthority());
        user.setEmail(userEntity.getEmail());
        user.setExtraTime(userEntity.getExtraTime());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setUsername(userEntity.getUsername());
    }
}
