package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.siedleckimateusz.nailsnatapp.entity.Authority;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewUser;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper(new BCryptPasswordEncoder());
    }

    @Test
    void mapToEntity() {
//    given
        NewUser newUser = NewUser.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("kowalski.jan@gmail.com")
                .phoneNumber("123123123")
                .username("jankowaslki")
                .password("abcd")
                .authority(Authority.CLIENT)
                .build();
//    when
        UserEntity userEntity = userMapper.toEntity(newUser);

//    then

        assertNotEquals(userEntity.getPassword(), newUser.getPassword());
        assertEquals(newUser.getFirstName(),userEntity.getFirstName());
    }

    @Test
    void mapNullValue(){
//        when
        UserEntity userEntity = userMapper.toEntity(null);
//        then
        assertNull(userEntity);
    }

}