package org.github..common.commons;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
public class PageList<T> implements Serializable {
    private Paginator paginator;
    private List list;

    public PageList(List<T> list) {

        if(list instanceof Page){
            paginator=new Paginator((Page)list);
            this.list=list;
        }
    }
    public PageList(){}

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
