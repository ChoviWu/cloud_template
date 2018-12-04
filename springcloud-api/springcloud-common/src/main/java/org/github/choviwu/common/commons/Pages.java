package org.github..common.commons;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

/**
 * @author
 * @date 2018/8/31
 * Description :
 */
@Data
public class Pages<T> extends Page<T> {

    private Integer pageNum;
    private Integer pageSize;

    public Pages(){
        super();
    }
    public Pages(int pageNum,int pageSize){
        super(pageNum,pageSize);
    }
}
