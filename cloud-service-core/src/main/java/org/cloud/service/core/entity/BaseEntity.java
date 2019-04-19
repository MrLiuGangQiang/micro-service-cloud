package org.cloud.service.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.entity
 * @remark base entity
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

	/**
	 * @type: {@link Integer}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 当前页 默认1 不参与序列化
	 */
	/**
	 * page default 1 no serialization
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @type {@link Integer}
	 */
	transient Integer page;
	/**
	 * page size default 10 no serialization
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @type {@link Integer}
	 */
	transient Integer pageSize;
	/**
	 * sort set no serialization
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @type {@link List<Sort>}
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
