package com.babuwyt.documentary.bean;

import com.babuwyt.documentary.entity.orderdetailsentity.OrderDetailsEntity;
import com.babuwyt.documentary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderDetailsBean extends BaseBean{

    private OrderDetailsEntity obj;

    public OrderDetailsEntity getObj() {
        return obj;
    }

    public void setObj(OrderDetailsEntity obj) {
        this.obj = obj;
    }
    /**
     * {
     "success": true,
     "msg": "成功",
     "obj": {
     "fsendcarno": "D7520170711001",
     "fpickuptime": 1499644800000,
     "fpicktime": 1499755160000,
     "ftaskstate": 5,
     "ordergood": "清洁能源",
     "pricture": [
     "2017/07/11/33-610113-590c2007f590496493296ca294ad60fc.jpg",
     "2017/07/11/33-610113-992bd9981434404b83e4229c1fce3a09.jpg",
     "2017/07/11/33-610113-d143818fc30e4cbb9939a043a68c6b8e.jpg",
     "2017/07/11/33-610113-970089adba734e9ab7b46005ceb0dbee.jpg"
     ],
     "fromtomap": {
     "TO": "陕西省西安市陕西省塔山乡沈家屯村以及段木丛村",
     "FROM": "陕西省西安市陕西省锦业时代"
     }
     }
     }
     */

}
