package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

/**
 * @author sertion
 * @date 2018/5/9
 * @since JDK1.8
 * @version 1.0
 */

@Alias("VoladorChapter")
public class Chapter extends AbstractModel {
    private String chapter_name;
    private String video_address;
    private String chapter_info;

    public String getChapter_info() {
		return chapter_info;
	}

	public void setChapter_info(String chapter_info) {
		this.chapter_info = chapter_info;
	}

	public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getVideo_address() {
        return video_address;
    }

    public void setVideo_address(String video_address) {
        this.video_address = video_address;
    }

    @Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }
}
