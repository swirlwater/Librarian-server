package com.whx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 借书类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("Borrow对象")
public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 书籍id
     */
    @ApiModelProperty("书名")
    private String bookName;

    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer num;

    /**
     * 借出时间
     */
    @ApiModelProperty(value = "借出时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lendTime;

    /**
     * 归还时间
     */
    @ApiModelProperty(value = "归还时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date repaidTime;

    /**
     * 借出状态
     */
    @ApiModelProperty("借出状态")
    private Integer station;
}
