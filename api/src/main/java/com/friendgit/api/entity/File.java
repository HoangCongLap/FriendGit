package com.friendgit.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "file")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class File {
    @Field("file_name")
    private String fileName;
    @Field("file_path")
    private String filePath;
    @Field("version_id")
    private String versionId;
    @Field("create_at")
    private Date createAt;
}
