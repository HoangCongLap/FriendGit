package com.friendgit.api.model;

import com.mongodb.internal.connection.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "repository")
public class Repo {

    private String _id;

    private String account_id;

    private String repository_name;

    private Date updated_time;

    private Date created_time;

    private String repository_star;

}
