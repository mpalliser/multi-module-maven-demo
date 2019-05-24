package com.example.model.converter;

import com.example.model.api.UserDto;
import com.example.model.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserConverter {

    public UserDto toApiModel(User domainModel) {

            UserDto apiModel = new UserDto();
        try {
            BeanUtils.copyProperties(apiModel, domainModel);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return apiModel;
    }

    public List<UserDto> toApiModel(List<User> domainList) {
        List<UserDto> userDtoList = new ArrayList<>();
        domainList.forEach(domainUser -> userDtoList.add(toApiModel(domainUser)));

        return userDtoList;
    }

    public List<UserDto> toApiModel(Iterable<User> domainList) {
        return StreamSupport.stream(domainList.spliterator(), false)
                .map(this::toApiModel)
                .collect(Collectors.toList());
    }
}
