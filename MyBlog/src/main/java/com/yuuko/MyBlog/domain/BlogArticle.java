package com.yuuko.MyBlog.domain;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName blog_article
 */
@TableName(value ="blog_article")
@Data
public class BlogArticle implements Serializable {
    /**
     * 文章id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String tid;

    /**
     * 作者uid
     */
    private String uid;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String context;

    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 浏览量
     */
    private Integer watchNum;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)//创建与修改时自动填充
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//创建与修改时自动填充
    private Date updateTime;

    /**
     * 状态
     */
    private Integer disable;

    /**
     * 封面
     */
    private String imgUrl;

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
        BlogArticle other = (BlogArticle) that;
        return (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getLikeNum() == null ? other.getLikeNum() == null : this.getLikeNum().equals(other.getLikeNum()))
            && (this.getWatchNum() == null ? other.getWatchNum() == null : this.getWatchNum().equals(other.getWatchNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDisable() == null ? other.getDisable() == null : this.getDisable().equals(other.getDisable()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getLikeNum() == null) ? 0 : getLikeNum().hashCode());
        result = prime * result + ((getWatchNum() == null) ? 0 : getWatchNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDisable() == null) ? 0 : getDisable().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", uid=").append(uid);
        sb.append(", title=").append(title);
        sb.append(", context=").append(context);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", watchNum=").append(watchNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", disable=").append(disable);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}