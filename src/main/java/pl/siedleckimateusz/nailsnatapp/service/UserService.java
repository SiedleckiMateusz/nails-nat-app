package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.Authority;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.UserMapper;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewUser;
import pl.siedleckimateusz.nailsnatapp.entity.model.UserForm;
import pl.siedleckimateusz.nailsnatapp.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo repo;
    private final UserMapper mapper;

    public UserService(UserRepo userRepo, UserMapper mapper) {
        this.repo = userRepo;
        this.mapper = mapper;
    }


    public UserEntity save(NewUser user) {
        return repo.save(mapper.toSave(user));
    }

    public Optional<UserEntity> findById(Long id){
        return repo.findById(id);
    }

    public List<UserEntity> findAll(){
        return repo.findAll();
    }

    public Optional<UserEntity> findByUserName(String username){
        return repo.findByUsername(username);
    }

    public UserEntity saveOrGetExist(String firstName, String lastName, String phoneNumber){
        Optional<UserEntity> userOpt = repo.findByFirstNameAndLastNameAndPhoneNumberContains(firstName, lastName, phoneNumber);

        return userOpt.orElseGet(() -> save(
                NewUser.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .phoneNumber(phoneNumber)
                        .username(firstName.substring(0,1).toLowerCase()+lastName.toLowerCase())
                        .password(phoneNumber)
                        .authority(Authority.CLIENT)
                        .build()));

    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> userByUsernameOpt = repo.findByUsername(s);
        Optional<UserEntity> userByEmailOpt = repo.findByEmail(s);

        if (userByEmailOpt.isPresent()){
            return userByEmailOpt.get();
        }
        if (userByUsernameOpt.isPresent()){
            return userByUsernameOpt.get();
        }

        throw new UsernameNotFoundException("Nie znaleziono użytkownika");
    }

    public UserEntity findLoggedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            Optional<UserEntity> byUserName = repo.findByUsername(username);
            return byUserName.orElseThrow(()->new UsernameNotFoundException("Nie ma użytkownika o podanej nazwie użytkownika"));
        }

        throw new UsernameNotFoundException("Nie udało się pobrać użytkownika");
    }

    public UserEntity save(UserForm userForm) {
        UserEntity userToSave = mapper.toSave(userForm);

        Optional<UserEntity> userOpt =
                repo.findByFirstNameAndLastNameAndPhoneNumberContains(userForm.getFirstName()
                        , userForm.getLastName(), userForm.getPhoneNumber());

        userOpt.ifPresent(userEntity -> userToSave.setId(userEntity.getId()));

        return repo.save(userToSave);
    }


}
