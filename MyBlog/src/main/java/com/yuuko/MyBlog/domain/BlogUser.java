package com.yuuko.MyBlog.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName blog_user
 */
@TableName(value ="blog_user")
@Data
public class BlogUser implements Serializable {
    /**
     * 用户uid
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String uid;

    /**
     * 用户名
     */
    private String username;

    private Integer disable;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 头像链接
     */
    private String avatarUrl;

    /**
     * 账户权限
     */
    private String userAuthority;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT) //创建时自动填充
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//创建与修改时自动填充
    private Date updateTime;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱号
     */
    private String emil;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BlogUser other = (BlogUser) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getAvatarUrl() == null ? other.getAvatarUrl() == null : this.getAvatarUrl().equals(other.getAvatarUrl()))
            && (this.getUserAuthority() == null ? other.getUserAuthority() == null : this.getUserAuthority().equals(other.getUserAuthority()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEmil() == null ? other.getEmil() == null : this.getEmil().equals(other.getEmil()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getAvatarUrl() == null) ? 0 : getAvatarUrl().hashCode());
        result = prime * result + ((getUserAuthority() == null) ? 0 : getUserAuthority().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getEmil() == null) ? 0 : getEmil().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", disable=").append(disable);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", userAuthority=").append(userAuthority);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", emil=").append(emil);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}