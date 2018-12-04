package org.github..api.web;

import org.github..common.util.DateUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author zhangxd
 */
public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        setValue(DateUtils.parseDate(text));
    }

}
