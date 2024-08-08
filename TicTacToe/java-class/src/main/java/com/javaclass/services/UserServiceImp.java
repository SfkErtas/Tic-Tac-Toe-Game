package com.javaclass.services;
import com.javaclass.entities.User;
import com.javaclass.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserServiceImp implements IUserService{

    @Autowired
    private final UserRepository userRepository;
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void saveUser(String name) {
        Optional<User> existingUser = userRepository.findByName(name);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            userRepository.save(user);
        }
        else {
            User user = new User();
            user.setName(name);
            userRepository.save(user);
        }
    }
    public void winner(String name){
        Optional<User> winner = userRepository.findByName(name);
        if (winner.isPresent()){
            User user = winner.get();
            user.setWinCount(user.getWinCount()+1);
            userRepository.save(user);
        }
    }
    public void loser(String name){
        Optional<User> loser = userRepository.findByName(name);
        if (loser.isPresent()){
            User user = loser.get();
            user.setLoseCount(user.getLoseCount()+1);
            userRepository.save(user);
        }
    }
    public void draw(String name){
        Optional<User> draw = userRepository.findByName(name);
        if (draw.isPresent()){
            User user = draw.get();
            user.setDrawCount(user.getDrawCount()+1);
            userRepository.save(user);
        }
    }
}
