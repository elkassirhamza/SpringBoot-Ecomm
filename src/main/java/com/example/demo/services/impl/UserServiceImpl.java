package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Roles;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UtilisateurEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.shared.dto.UserDto;
import com.example.demo.shared.dto.Services.Utils;




@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
//	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
//	
	@Autowired 
	Utils utils;
	
//	@Autowired
//	Roles role;
	
	
	@Override
	public UserDto createUser(UserDto user) {

		UtilisateurEntity checkUser = (UtilisateurEntity) userRepository.findByEmail(user.getEmail());
		
		if(checkUser != null) throw new RuntimeException("User Already Exists !");
		
		UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
		
		BeanUtils.copyProperties(user, utilisateurEntity);
		
		utilisateurEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//userEntity.setEncryptedPassword("text password");
		utilisateurEntity.setUserId(utils.generateUserId(32));
		
		Roles role = new Roles();
		
		role.setRole_id(1L); 
		
		utilisateurEntity.setRole(role);
		
		UserEntity newUser = userRepository.save(utilisateurEntity);
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(newUser, userDto);
		
		return userDto;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}


	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}


	@Override
	public UserDto getUserByUserId(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}


	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		
		userEntity.setFirstName(userDto.getFirstName());
		
		userEntity.setLastName(userDto.getLastName());
		
		UserEntity userUpdated =  userRepository.save(userEntity);
		
		UserDto user = new UserDto();

		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}


	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		

		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		
		userRepository.delete(userEntity);
		
	}


	@Override
	public List<UserDto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		
		if(page > 0 ) page = page -1;
		
		
		List<UserDto> usersDto = new ArrayList<>();
		
		Pageable pageable = PageRequest.of(page, limit);
		
		Page<UserEntity> userPage = userRepository.findAll(pageable);
		
		List<UserEntity> users = userPage.getContent();
		
		for(UserEntity userEntity : users) {
			UserDto user = new UserDto();
			
			BeanUtils.copyProperties(userEntity, user);
			
			usersDto.add(user);
		}
		
		
		
		return usersDto;
	}

}
