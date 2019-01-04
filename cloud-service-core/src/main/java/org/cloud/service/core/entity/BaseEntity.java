package org.cloud.service.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月10日
 * @description: 顶层实体类
 */
public class BaseEntity implements Serializable {

	/**
	 * @type: {@link long}
	 * @author: LiuGangQiang
	 * @date: 2018年12月10日
	 * @description: 序列化唯一ID
	 */
	private static final long serialVersionUID = 5487925883335071700L;
	/**
	 * @type: {@link Integer}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 当前页 默认1 不参与序列化
	 */
	transient Integer page;
	/**
	 * @type: {@link Integer}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 每页记录数 默认10 不参与序列化
	 */
	transient Integer pageSize;
	/**
	 * @type: {@link List<Sort>}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 排序list 不参与序列化
	 */
	transient List<Sort> sorts;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if (page != null) {
			this.page = page > 0 ? page : 1;
		} else {
			this.page = 1;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize != null) {
			this.pageSize = pageSize > 0 ? pageSize : 10;
		} else {
			this.pageSize = 1;
		}
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}
}
