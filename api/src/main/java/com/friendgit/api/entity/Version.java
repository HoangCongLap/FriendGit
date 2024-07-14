package com.friendgit.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "versions")

public class Version {
    @Id
    private String id;
    @Field("project_id")
    private String projectId;
    @Field("version_name")
    private String versionName;

}
