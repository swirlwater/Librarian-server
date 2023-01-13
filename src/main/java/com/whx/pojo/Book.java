package com.whx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 书籍类
 * </p>
 *
 * @author wu
 * @since 2023-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("Book对象")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 书名
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
    private String num;

    /**
     * 出版社
     */
    @ApiModelProperty("出版社")
    private String press;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String content;

}
