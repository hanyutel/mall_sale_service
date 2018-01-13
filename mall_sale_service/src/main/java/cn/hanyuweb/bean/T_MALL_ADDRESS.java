package cn.hanyuweb.bean;

import java.io.Serializable;

public class T_MALL_ADDRESS implements Serializable{

	private int id;
	private String dz_mch;
	private String dzzt;
	private int yh_id;
	private String shjr;
	private String lxfsh;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDz_mch() {
		return dz_mch;
	}

	public void setDz_mch(String dz_mch) {
		this.dz_mch = dz_mch;
	}

	public String getDzzt() {
		return dzzt;
	}

	public void setDzzt(String dzzt) {
		this.dzzt = dzzt;
	}

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public String getShjr() {
		return shjr;
	}

	public void setShjr(String shjr) {
		this.shjr = shjr;
	}

	public String getLxfsh() {
		return lxfsh;
	}

	public void setLxfsh(String lxfsh) {
		this.lxfsh = lxfsh;
	}

}
