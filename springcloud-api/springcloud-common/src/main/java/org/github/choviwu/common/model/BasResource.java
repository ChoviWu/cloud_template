package org.github..common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Accessors(prefix = )
@Table(name = "bas_resource_t")
public class BasResource implements Serializable{
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 资源代码
     */
    @Column(name = "resource_code")
    private String resourceCode;

    /**
     * 代码名称
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 1-菜单 2-按钮
     */
    @Column(name = "resource_type")
    private Byte resourceType;

    /**
     * url链接
     */
    @Column(name = "resource_url")
    private String resourceUrl;

    /**
     * 父菜单ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "resource_sort")
    private Integer resourceSort;

}