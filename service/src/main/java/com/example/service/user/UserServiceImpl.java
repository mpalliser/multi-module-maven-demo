package com.example.service.user;

import com.example.model.api.UserDto;
import com.example.model.converter.UserConverter;
import com.example.model.filter.UserFilter;
import com.example.repository.UserRepository;
import com.example.repository.predicates.UserPredicates;
import com.querydsl.core.BooleanBuilder;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDto> findAll() {
        return userConverter.toApiModel(userRepository.findAll());
    }

    public List<UserDto> findAll(UserFilter userFilter) {
        return userConverter.toApiModel(userRepository.findAll(buildBooleanBuilder(userFilter)));
    }

    private BooleanBuilder buildBooleanBuilder(UserFilter userFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (!StringUtils.isEmpty(userFilter.getName())) {
            booleanBuilder.and(UserPredicates.userNameLike(userFilter.getName()));
        }

        if (!Objects.isNull(userFilter.getId())) {
            booleanBuilder.and(UserPredicates.userIdEq(userFilter.getId()));
        }

        return booleanBuilder;
    }
}
