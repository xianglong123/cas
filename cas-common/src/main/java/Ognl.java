

import com.cas.owner.utils.MapUtils;
import com.cas.owner.utils.StringUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @Authen xiang_long
 * 作用于mapper 很实在的应用  mapper 可以用@调用
 */
public class Ognl {
    /**
     * 判断是否为空
     *
     * @param o 需判断的目标对象
     * @return true=空 or false=非空
     * @throws IllegalArgumentException 非法参数
     */
    public static boolean isEmpty(Object o) throws IllegalArgumentException {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return StringUtil.isEmpty((String) o);
        } else if (o instanceof Collection) {
            return CollectionUtils.isEmpty((Collection) o);
        } else if (o.getClass().isArray()) {
            return ArrayUtils.isEmpty((Object[]) o);
        } else if (o instanceof Map) {
            MapUtils.isEmpty((Map) o);
        } else if (o instanceof Date) {
            return o == null;
        } else if (o instanceof Number) {
            return o == null;
        } else if (o instanceof Boolean) {
            return o == null;
        } else {
            throw new IllegalArgumentException("Illegal argument typeHandler,must be : Map,Collection,Array,String. but was:" + o.getClass());
        }

        return false;
    }

    /**
     * 判断是否为空
     *
     * @param o 需判断的目标对象
     * @return true=非空 or false=空
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 判断是否为空
     *
     * @param o 需判断的目标对象
     * @return true=非空 or false=空
     */
    public static boolean isNotEmpty(Long o) {
        return !isEmpty(o);
    }
}
