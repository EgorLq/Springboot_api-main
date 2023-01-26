package com.example.demo.services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Diary;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserServicesImpl  implements  UserServices{
    private  final UserRepository repository ;
    private Supplier<? extends Throwable> ProfileNotFoundException;

    @Override
    public UserDTO saveUser(UserDTO request) {
        return (UserDTO) repository.save(request);
    }



    @Override
    public Optional<User> getUserById(Integer id) throws Throwable {
        return Optional.of(repository.findById(id).orElseThrow(ProfileNotFoundException));
    }

    @Override
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }


}
