package org.ranji.lemon.volador.model.course;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorCarouse")
public class Carouse extends AbstractModel{

	/**
	 * 轮播图
	 * @author 范小亚
	 * @date 2018/6/4
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = 8872159406969684849L;
	private String title;
	private String description;
	private String chainedAddress;
	private String image;
	private Date showTime;
	private String tag;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChainedAddress() {
		return chainedAddress;
	}
	public void setChainedAddress(String chainedAddress) {
		this.chainedAddress = chainedAddress;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }
}
