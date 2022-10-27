package com.yuuko.MyBlog.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName blog_comment
 */
@TableName(value ="blog_comment")
@Data
public class BlogComment implements Serializable {
    /**
     * 评论id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String cid;

    /**
     * 文章id
     */
    private String tid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 回复评论id
     */
    private String fromCid;

    /**
     * 评论内容
     */
    private String context;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//创建与修改时自动填充
    private Date updateTime;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//创建与修改时自动填充
    private Date createTime;

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
        BlogComment other = (BlogComment) that;
        return (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getFromCid() == null ? other.getFromCid() == null : this.getFromCid().equals(other.getFromCid()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getFromCid() == null) ? 0 : getFromCid().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", tid=").append(tid);
        sb.append(", uid=").append(uid);
        sb.append(", fromCid=").append(fromCid);
        sb.append(", context=").append(context);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}