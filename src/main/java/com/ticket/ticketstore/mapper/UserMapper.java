package com.ticket.ticketstore.mapper;

import java.util.List;

import org.mapstruct.*;

import com.ticket.ticketstore.dto.*;
import com.ticket.ticketstore.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserGetDTO toDto(User user);
    List<UserGetDTO> toDtos(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserPostDTO dto);
    List<User> toUsers(List<User> users);
}
