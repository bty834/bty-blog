package com.bty.blog.util;

import com.bty.blog.domain.PageResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bty
 * @date 2022/12/10
 * @since 1.8
 **/

public class PageUtil {

    public static final String PAGE_NUM="pageNum";
    public static final String PAGE_SIZE="pageSize";
    public static final String ORDER_BY="order";

    public static final Map<Integer,String> ORDER_SQL_MAP = new HashMap<>();

    static {
        ORDER_SQL_MAP.put(0,"created desc");
        ORDER_SQL_MAP.put(1,"created asc");
    }
    public static void helpPage(){
        String pageNumParam = ServletUtil.getParam(PAGE_NUM);
        if(pageNumParam==null){
            throw new RuntimeException("pls provide pageNum param");
        }
        String pageSizeParam = ServletUtil.getParam(PAGE_SIZE);
        if(pageSizeParam==null){
            throw new RuntimeException("pls provide pageSize param");
        }
        String orderParam = ServletUtil.getParam(ORDER_BY);
        if(orderParam==null){
            throw new RuntimeException("pls provide order param");
        }

        int pageNum = Integer.parseInt(pageNumParam);
        int pageSize= Integer.parseInt(pageSizeParam);

        String orderSql = ORDER_SQL_MAP.get(Integer.parseInt(orderParam));
        if(orderSql==null){
            throw new RuntimeException("page order not support");
        }
        PageHelper.startPage(pageNum, pageSize,orderSql).setReasonable(true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static PageResponse pageResponse(List<?> list)
    {
        long total = new PageInfo(list).getTotal();
        return PageResponse.success(list, total);
    }

    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}
