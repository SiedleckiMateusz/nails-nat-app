package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.UserMapper;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewUser;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repo.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s+" not found"));
    }
}
