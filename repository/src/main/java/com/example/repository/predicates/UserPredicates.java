package com.example.repository.predicates;

import com.example.model.domain.QUser;
import com.querydsl.core.types.Predicate;

import java.util.List;

public class UserPredicates {

    private UserPredicates() {
    }

    public static Predicate userNameLike(String name) {
        return QUser.user.name.contains(name);
    }

    public static Predicate surnameLike(String surname) {
        return QUser.user.surname.contains(surname);
    }

    public static Predicate userIdEq(Long id) {
        return QUser.user.id.eq(id);
    }
}
