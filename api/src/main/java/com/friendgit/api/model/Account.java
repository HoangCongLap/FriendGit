package com.friendgit.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account")
public class Account {

    private String _id;

    private String username;

    private String password;

    private String phone_number;

    private String email;

    private String profile_name;

}
