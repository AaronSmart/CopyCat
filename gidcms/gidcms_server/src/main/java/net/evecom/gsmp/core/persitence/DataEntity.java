/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.persitence;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * <b>数据操作实体类</b>
 * <p>
 * 主要用于能够增删改的实体，如果仅有查询的实体可不必继承， 其中将审计所要记录的创建时间，修改时间，创建人，修改人进行统一审计记录，
 * 并可通过<code>@PrePersist</code>,<code>@PreUpdate</code>,<code>@PreRemove</code>等事件进行实体类监听，
 * 可自定义实现这些事件
 * </p>
 *
 * @author Nick Lv
 * @created 2017 /07/06 17:36:44
 * @version --添加版本信息
 * @since Version 1.0
 * @see --添加类中引用的相关类和接口
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DataEntity extends BaseEntity{

//    使用序列故无法提成通用，如果使用普通id自增方案可共用
//    @Id
//    @Column(name = "ID", nullable = false, precision = 0)
//    @SequenceGenerator(name = "my_sequence", sequenceName = "gsmp.GSMP_LOC_ADDRESSES_S", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
//    @Getter@Setter private Long id;
    /**
     * 创建时间
     */
    private Date timeCreated;
    /**
     * 创建人
     */
    private Long creatorId;
    /**
     * 创建机构
     */

    private Long createOrgId;
    /**
     * 修改人
     */

    private Long modifierId;
    /**
     * 修改时间
     */

    private Date timeModified;

    /**
     * 描述： TODO..
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:15:58 Pre persist.
     */
    @PrePersist
    public void prePersist(){
        //设置机构 create_org_id
//        this.setCreatorOrgId(new Long(11111));
        this.populateAuditFields();
    }

    /**
     * 描述： TODO..
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:15:55 Pre update.
     */
    @PreUpdate
    public void preUpdate(){
//        this.populateAuditFields();
    }

    /**
     * 描述： TODO..
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:15:52 Pre remove.
     */
    @PreRemove
    public void preRemove() {
        //do something on remove events
    }

    /**
     * 描述： TODO..
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:15:49 Populate audit fields.
     */
    private void populateAuditFields() {
//        do something
        this.setCreatorId(11111111L);
    }

    /**
     * 描述： Gets time created.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     * @return the time created
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_CREATED", nullable = true)
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * 描述： Sets time created.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     * @param timeCreated
     *            the time created
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * 描述： Gets creator id.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     * @return the creator id
     */
    @CreatedBy
    @Column(name = "CREATOR_ID", nullable = true, precision = 0)
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * 描述： Sets creator id.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     * @param creatorId
     *            the creator id
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 描述： Gets creator org id.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     * @return the creator org id
     */
    @Column(name = "CREATE_ORG_ID", nullable = true, precision = 0)
    public Long getCreateOrgId() {
        return createOrgId;
    }

    /**
     * 描述： Sets creator org id.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     * @param createOrgId
     *            the creator org id
     */
    public void setCreateOrgId(Long createOrgId) {
        this.createOrgId = createOrgId;
    }

    /**
     * 描述： Gets modifier id.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:32
     * @return the modifier id
     */
    @LastModifiedBy
    @Column(name = "MODIFIER_ID", nullable = true, precision = 0)
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 描述： Sets modifier id.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:32
     * @param modifierId
     *            the modifier id
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 描述： Gets time modified.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:32
     * @return the time modified
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_MODIFIED", nullable = true)
    public Date getTimeModified() {
        return timeModified;
    }

    /**
     * 描述： Sets time modified.
     *
     * @author Nick Lv
     * @created 2017 /07/06 17:41:32
     * @param timeModified
     *            the time modified
     */
    public void setTimeModified(Date timeModified) {
        this.timeModified = timeModified;
    }
}



