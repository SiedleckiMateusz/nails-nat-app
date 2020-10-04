package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewUser;
import pl.siedleckimateusz.nailsnatapp.entity.model.UserToSession;

@Component
public class UserMapper implements Mapper<NewUser,UserEntity> {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity toSave(NewUser newUser){
        return newUser == null? null : UserEntity.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .username(newUser.getUsername())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .email(newUser.getEmail())
                .phoneNumber(newUser.getPhoneNumber())
                .authority(newUser.getAuthority())
                .extraTime(newUser.getExtraTime())
                .build();
    }

    public UserToSession toSession(UserEntity o){
        if (o==null)return null;

        return UserToSession.builder()
                .authority(o.getAuthority())
                .email(o.getEmail())
                .extraTime(o.getExtraTime())
                .firstName(o.getFirstName())
                .lastName(o.getLastName())
                .id(o.getId())
                .phoneNumber(o.getPhoneNumber())
                .username(o.getUsername())
                .build();
    }


}
