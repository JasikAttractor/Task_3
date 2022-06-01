package kz.mukysh.task_3.controllers;

import kz.mukysh.task_3.dto.UserDTO;
import kz.mukysh.task_3.models.User;
import kz.mukysh.task_3.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users/")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @RequestMapping(value="{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long userId){
        if(userId==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.getOne(userId);
        if(user==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @RequestMapping(value="",method = RequestMethod.POST)
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO user){
        HttpHeaders headers = new HttpHeaders();
        if(user==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.userService.save(user);
        return new ResponseEntity<>(user,headers,HttpStatus.CREATED);
    }
    @RequestMapping(value="",method=RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(UserDTO user){
        HttpHeaders headers = new HttpHeaders();
        if(user==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userService.save(user);
        return new ResponseEntity<>(user,headers,HttpStatus.OK);
    }
    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id){
            User user = userService.getOne(id);
            if(user==null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.findAll();
        if(users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
