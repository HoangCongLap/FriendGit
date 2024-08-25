package com.friendgit.api.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "versions")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Version {
    @Id
    String id;
    @Field("project_id")
    String projectId;
    @Field("version_name")
    String versionName;

}
