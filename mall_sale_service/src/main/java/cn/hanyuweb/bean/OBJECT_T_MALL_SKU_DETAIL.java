package cn.hanyuweb.bean;

import java.util.List;

public class OBJECT_T_MALL_SKU_DETAIL extends T_MALL_SKU{

	private T_MALL_PRODUCT spu;
	private List<T_MALL_PRODUCT_IMAGE> list_image;
	private List<OBJECT_T_MALL_AV_NAME> av_name;
	public T_MALL_PRODUCT getSpu() {
		return spu;
	}
	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}
	public List<T_MALL_PRODUCT_IMAGE> getList_image() {
		return list_image;
	}
	public void setList_image(List<T_MALL_PRODUCT_IMAGE> list_image) {
		this.list_image = list_image;
	}
	public List<OBJECT_T_MALL_AV_NAME> getAv_name() {
		return av_name;
	}
	public void setAv_name(List<OBJECT_T_MALL_AV_NAME> av_name) {
		this.av_name = av_name;
	}
	
}
