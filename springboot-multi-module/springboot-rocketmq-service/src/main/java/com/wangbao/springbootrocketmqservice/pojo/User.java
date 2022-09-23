package com.wangbao.springbootrocketmqservice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;

    private String nickName;

    private String sex;

    private Integer phone;

    public void getAll()
    {
        System.out.println(this.userName);
        System.out.println(this.password);
        System.out.println(this.nickName);
        System.out.println(this.sex);
        System.out.println(this.phone);
    }


}
