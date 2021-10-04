package com.example.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.demo.dtos.UserMsDto;
import com.example.demo.entities.User;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper Instance = Mappers.getMapper(UserMapper.class);
	
	// User to User DTO
	@Mapping(source = "email",target = "emailaddress")
	UserMsDto userTouserMsDto(User user);
	
	// List of Users to List of User DTO
	List<UserMsDto> usersTousersMsDtos(List<User> users);
}
