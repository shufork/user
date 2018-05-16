package me.shufork.biz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_role_authority")
@DynamicInsert
@DynamicUpdate
public class RolePermission implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "f_id",length = 64)
    private String id;

    @Column(name = "f_role_ref",nullable = false,length = 64)
    private String role;

    @Column(name = "f_user_ref",nullable = false,length = 64)
    private String user;
}
