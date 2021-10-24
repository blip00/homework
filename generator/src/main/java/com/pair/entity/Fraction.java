package com.pair.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数字实体类（全部表示为分数）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fraction {

    /** 整数部分：整数和一般真分数该值为0 */
    private Integer integer;

    /** 分母：整数该值为1 */
    private Integer denominator;

    /** 分子 */
    private Integer numerator;

}
