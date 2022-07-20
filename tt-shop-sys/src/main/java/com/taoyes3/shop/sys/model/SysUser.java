package com.taoyes3.shop.sys.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -3306276425762342532L;

    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度要在2-20之间")
    private String username;

    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = "0?1[0-9]{10}\",message = \"请输入正确的手机号")
    private String mobile;

    private Integer status;

    private Long createId;

    private Date createTime;
}
