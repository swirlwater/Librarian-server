package com.whx.pojo;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @TableId
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private String num;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String userId;

    /**
     * 书籍id
     */
    @ApiModelProperty("书籍id")
    private String bookId;

    /**
     * 借出时间
     */
    @ApiModelProperty("借出时间")
    private LocalDate lendTime;

    /**
     * 归还时间
     */
    @ApiModelProperty("归还时间")
    private LocalDate repaidTime;

    /**
     * 借出状态
     */
    @ApiModelProperty("借出状态")
    private String station;
}
