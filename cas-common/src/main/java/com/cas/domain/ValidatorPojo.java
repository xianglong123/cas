package com.cas.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 15:03 2020-02-22
 * @version: V1.0
 * @review: 这里列举了大部分可以用到的JSR-303限制，需要@valid 打开验证开关
 */
@Data
public class ValidatorPojo {

    @NotNull(message = "id 不能为空")
    private Long id;

    @Future(message = "需要一个将来的时间")
    // @Past //只能是过去的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 日期格式化转换
    @NotNull
    private Date date;

    @NotNull
    @DecimalMin(value = "0.01") //最小值
    @DecimalMax(value = "10000.00") //最大值
    private Double doubleValue;

    @NotNull
    @Min(value = 10, message = "最小值为10")
    @Max(value = 100, message = "最大值为100")
    private Integer integer;

    @NotNull
    @Range(min = 1, max = 100, message = "范围在1到100之间")
    private Long rang;

    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 20, max = 30, message = "字符串长度要求在20到30之间。")
    private String size;

}
