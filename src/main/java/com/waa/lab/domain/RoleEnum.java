package com.waa.lab.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.waa.lab.exception.InvalidRoleException;

import java.util.stream.Stream;

public enum RoleEnum {
    ADMIN, USER;

    public RoleEnum getEnum(String name) throws InvalidRoleException {
        // Case-insensitive to be more lenient.
        String upperCase = name.toUpperCase();
        RoleEnum matchingRole;
        if(ADMIN.name().equals(upperCase))  {
            matchingRole = ADMIN;
        } else if(USER.name().equals(upperCase)) {
            matchingRole = USER;
        } else {
            throw new InvalidRoleException(name);
        }

        return matchingRole;
    }


}
