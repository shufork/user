package me.shufork.biz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="t_api_resource")
@DynamicInsert
@DynamicUpdate
public class ApiResource {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "f_id",length = 64)
    private String id;

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
