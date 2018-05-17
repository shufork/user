package me.shufork.biz.domain;

import lombok.Data;
import me.shufork.common.enums.UserStatusEnums;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="t_user")
@DynamicInsert
@DynamicUpdate
public class User implements Serializable{
    @Version
    @Column(name = "z_version")
    private Long version;

    @Column(name = "z_created_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "z_modified_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTime;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "f_id",length = 64)
    private String id;

    @Column(name = "f_login_name",nullable = false,unique = true,length = 16)
    private String loginName;

    @Column(name = "f_password",nullable = false,length = 128)
    private String password;

    @Column(name = "f_display_name",nullable = false,length = 32)
    private String displayName;

    @Column(name = "f_email",length = 64)
    private String email;

    @Column(name = "f_mobile",length = 20)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "f_status",nullable = false)
    private UserStatusEnums status;

}
