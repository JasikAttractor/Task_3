package kz.mukysh.task_3.services;

import kz.mukysh.task_3.dto.UserDTO;
import kz.mukysh.task_3.exceptions.ResourceNotFoundException;
import kz.mukysh.task_3.models.User;
import kz.mukysh.task_3.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }
    public UserDTO save(UserDTO userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .build();
        repository.save(user);
        return UserDTO.from(user);
    }
    public User getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = getOne(id);
        userDTO.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        repository.save(user);
        return UserDTO.from(user);
    }
    public List<User> delete(Long id) {
        User user = getOne(id);
        repository.delete(user);
        return repository.findAll();
    }

}
