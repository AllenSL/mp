package com.asl.mp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName User
 * @Description
 * @Author asl
 * @Date 2021/4/27 13:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "user")
public class User implements Serializable{
    private static final long serialVersionUID = 3305969324716325848L;

    // 必须指定一个id，
    @Id
    private String id;
    // 这里配置了分词器，字段类型，可以不配置，默认也可
    @NotEmpty(message = "name不能为空")
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String name;

    private Integer age;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String sex;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String desc;
}
