package com.friendgit.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "file")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class File {

    @Field("project_id")
    String projectId;

    @Field("file_name")
    String fileName;

    @Field("size")
    String size;

    @Field("create_by_user_id")
    String createdByUserId;

    @Field("modified_by_user_id")
    String modifiedByUserId;

    @Field("file_path")
    String path;

    @Field("create_at")
    Date createAt;

    @Field("create_date")
    Date createdUpDate;
}
