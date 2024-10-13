package com.example.circling.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.circling.entity.Role;
import com.example.circling.entity.User;
import com.example.circling.form.SignupForm;
import com.example.circling.form.UserEditPasswordForm;
import com.example.circling.repository.RoleRepository;
import com.example.circling.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;        
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    public User create(SignupForm signupForm,String roles) {
        User user = new User();
        Role role = roleRepository.findByName(roles);
        user.setName(signupForm.getName());
        user.setEmail(signupForm.getEmail());
        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        user.setRole(role);
        user.setEnabled(false);        
        
        return userRepository.save(user);
    }
    
    public boolean isEmailRegistered(String email) {
        User user = userRepository.findByEmail(email);  
        return user != null;
    }  
    
    public boolean isSamePassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }
    
    @Transactional
    public void enableUser(User user) {
        user.setEnabled(true); 
        userRepository.save(user);
    }
    @Transactional
    public User passwordupdate(User user,UserEditPasswordForm userEditForm) {
    	user.setPassword(passwordEncoder.encode(userEditForm.getPassword()));
    	return userRepository.save(user);
    }
    @Transactional
    public boolean isSameEncodePassword(User user,String password) {
    	return user.getPassword().equals(passwordEncoder.encode(password));
    }
}
