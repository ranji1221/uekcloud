package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;

/**
 * @author sertion
 * @date 2018/5/10
 * @verison 1.0
 * @since JDK1.8
 */
@Alias("VoladorAccessory")
public class Accessory extends AbstractModel {
    private String accessory_name;
    private String getAccessory_address;

    public String getAccessory_name() {
        return accessory_name;
    }

    public void setAccessory_name(String accessory_name) {
        this.accessory_name = accessory_name;
    }

    public String getGetAccessory_address() {
        return getAccessory_address;
    }

    public void setGetAccessory_address(String getAccessory_address) {
        this.getAccessory_address = getAccessory_address;
    }
}
