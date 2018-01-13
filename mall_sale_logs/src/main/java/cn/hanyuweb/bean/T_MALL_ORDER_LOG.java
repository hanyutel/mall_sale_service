package cn.hanyuweb.bean;

import java.math.BigDecimal;
import java.util.Date;

public class T_MALL_ORDER_LOG {
	private int id;
	private Date dd_shj;
	private int dd_id;
	private int zhfpt_id;
	private BigDecimal zhfje;
	private String zhfzhh;
	private String shkzhh;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDd_shj() {
		return dd_shj;
	}
	public void setDd_shj(Date dd_shj) {
		this.dd_shj = dd_shj;
	}
	public int getDd_id() {
		return dd_id;
	}
	public void setDd_id(int dd_id) {
		this.dd_id = dd_id;
	}
	public int getZhfpt_id() {
		return zhfpt_id;
	}
	public void setZhfpt_id(int zhfpt_id) {
		this.zhfpt_id = zhfpt_id;
	}
	public BigDecimal getZhfje() {
		return zhfje;
	}
	public void setZhfje(BigDecimal zhfje) {
		this.zhfje = zhfje;
	}
	public String getZhfzhh() {
		return zhfzhh;
	}
	public void setZhfzhh(String zhfzhh) {
		this.zhfzhh = zhfzhh;
	}
	public String getShkzhh() {
		return shkzhh;
	}
	public void setShkzhh(String shkzhh) {
		this.shkzhh = shkzhh;
	}
	

}
