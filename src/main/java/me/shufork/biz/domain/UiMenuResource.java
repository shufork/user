package me.shufork.biz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="t_ui_menu_resource")
@DynamicInsert
@DynamicUpdate
public class UiMenuResource {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "f_id",length = 64)
    private String id;

    @Column(name = "f_enabled",nullable = false)
    private Boolean enabled;

    @Column(name = "f_create_by",nullable = false,length = 32)
    private String createBy;

    @Column(name = "f_ui_text",nullable = false,length = 32)
    private String uiText;

    @Column(name = "f_ui_icon",nullable = false)
    private String uiIcon;

    @Column(name = "f_ui_node",nullable = false,length = 64)
    private String uiNode;

    @Column(name = "f_ui_parent_node",length = 64)
    private String uiParentNode;
}
