package me.shufork.biz.domain;

import lombok.Data;
import me.shufork.biz.enums.RoleType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_role")
public class Role implements Serializable {

    @Column(name = "z_created_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "z_modified_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTime;

    @Version
    @Column(name = "z_version")
    private Long version;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "f_id",length = 64)
    private String id;

    @Column(name = "f_name",nullable = false,unique = true,length = 64)
    private String name;

    @Column(name = "f_description",nullable = false,unique = true,length = 64)
    private String description;

    @Column(name = "f_enabled",nullable = false)
    private Boolean enabled;

    @Column(name = "f_type",nullable = false,length = 16)
    @Enumerated(EnumType.STRING)
    private  RoleType type;

    @Column(name = "f_create_by",nullable = false,length = 32)
    private String createBy;
}
