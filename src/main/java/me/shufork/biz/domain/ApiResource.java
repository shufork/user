package me.shufork.biz.domain;

import lombok.Data;
import me.shufork.biz.enums.ApiMethod;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="t_api_resource")
@DynamicInsert
@DynamicUpdate
public class ApiResource {

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

    @Column(name = "f_scope",nullable = false)
    private String scope;

    @Column(name = "f_name",nullable = false,length = 64)
    private String name;

    @Column(name = "f_enabled",nullable = false)
    private Boolean enabled;

    @Column(name = "f_create_by",nullable = false,length = 32)
    private String createBy;

    @Column(name = "f_api_method",nullable = false,length = 16)
    @Enumerated(EnumType.STRING)
    private ApiMethod method;

    @Column(name = "f_api_path",nullable = false)
    private String path;

}
