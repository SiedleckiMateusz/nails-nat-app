package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewUser;

@Component
public class UserMapper implements Mapper<NewUser,UserEntity> {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity toEntity(NewUser newUser){
        return newUser == null? null : UserEntity.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .username(newUser.getUsername())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .email(newUser.getEmail())
                .phoneNumber(newUser.getPhoneNumber())
                .authority(newUser.getAuthority())
                .build();
    }


}
