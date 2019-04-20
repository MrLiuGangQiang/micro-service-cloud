package org.cloud.service.core.result;

import java.util.List;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.result
 * @remark multiline rows
 */
public class MultiLine<T> {

	/**
	 * total rows
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link Long}
	 */
	private Long total;

	/**
	 * multiline records
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link List<Map<String,Object>>}
	 */
	private List<T> rows;

	/**
	 * constructor by total and rows
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param total
	 * @param rows
	 */
	public MultiLine(long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	public MultiLine() {
	}

	public Long getTotal() {
		return total;
	}

	public MultiLine<T> setTotal(Long total) {
		this.total = total;
		return this;
	}

	public List<T> getRows() {
		return rows;
	}

	public MultiLine<T> setRows(List<T> rows) {
		this.rows = rows;
		return this;
	}
}
