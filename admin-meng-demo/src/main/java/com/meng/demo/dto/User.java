package com.meng.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/13 9:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public interface simple{}
    public interface detail extends simple{}

    @JsonView(simple.class)
    private Integer id;

    @JsonView(simple.class)
    @NotNull(message = "姓名不能为空")
    private String name;

    @JsonView(simple.class)
    @Min(value = 0,message = "年龄不能小于0")
    private Integer age;

    @JsonView(detail.class)
    @Length(min = 6,message = "密码不能小于6位")
    private String password;
}