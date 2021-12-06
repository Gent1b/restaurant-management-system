package com.tahadonuk.restaurantmanagementsystem.service;

import com.tahadonuk.restaurantmanagementsystem.data.UserRole;
import com.tahadonuk.restaurantmanagementsystem.data.entity.user.AppUser;
import com.tahadonuk.restaurantmanagementsystem.dto.Address;
import com.tahadonuk.restaurantmanagementsystem.dto.Email;
import com.tahadonuk.restaurantmanagementsystem.dto.Name;
import com.tahadonuk.restaurantmanagementsystem.data.repository.UserRepository;
import com.tahadonuk.restaurantmanagementsystem.dto.UserDTO;
import com.tahadonuk.restaurantmanagementsystem.exception.UserConflictException;
import com.tahadonuk.restaurantmanagementsystem.exception.UserNotFoundException;
import com.tahadonuk.restaurantmanagementsystem.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public AppUser saveUser(UserDTO userDTO) throws Exception{
        if(isExists(userDTO.getEmail())) {
            throw new UserConflictException("An employee with email  '" + userDTO.getEmail() + "' is already exists.");
        } else {
            AppUser user = new AppUser();

            user.setPassword(encoder.encode(userDTO.getPassword()));
            user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(userDTO.getDateOfBirth()));
            user.setJoinDate(new Date());
            user.setName(new Name(userDTO.getFirstName(), userDTO.getLastName()));
            user.setAddress(new Address(userDTO.getStreet(), userDTO.getApartment()));
            user.setRole(UserRole.USER); // default value
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setSalary(0); // default value

            userRepository.save(user);

            return user;
        }
    }

    public void deleteUser(long id) {
        if(isExist(id)) {
            userRepository.deleteById(id);
        }
        else throw new UserNotFoundException("There is no such employee with given id: '" + id + "'");
    }

    public AppUser getUserById(long id) {
        if(isExist(id)) {
            return userRepository.findById(id).get();
        }
        else throw new UserNotFoundException("There is no such employee with given id: '" + id + "'");
    }

    public List<AppUser> getUsersByName(Name name) {
        if(userRepository.existsByName(name)) {
            List<AppUser> users = userRepository.findAppUsersByName(name);

            return users;
        }
        else throw new UserNotFoundException("There is no employee with name '" + name.getFirstName() + " " + name.getLastName() + "'");
    }

    public AppUser getUserByNumber(String phoneNumber) {
        if(userRepository.existsByPhoneNumber(phoneNumber)) {
            Optional<AppUser> emp = userRepository.findAppUserByPhoneNumber(phoneNumber);
            return emp.get();
        }
        else throw new UserNotFoundException("There is no employee with given phone number: '" + phoneNumber + "'");
    }

    public List<AppUser> getUsersByRole(UserRole role) {
        List<AppUser> users = userRepository.findAppUsersByRole(role);
        return users;
    }

    public AppUser getUserByEmail(String email) {
        if(isExists(email)) {
            return userRepository.findAppUserByEmail(email).get();
        }
        else throw new NotFoundException("There is no employee with email: '" + email + "'");
    }

    public boolean isExist(long id) {
        if(userRepository.existsById(id)) {
            return true;
        }
        else return false;
    }

    public boolean isExists(String email) {
        if(userRepository.existsByEmail(email)) {
            return true;
        }
        else return false;
    }
}
