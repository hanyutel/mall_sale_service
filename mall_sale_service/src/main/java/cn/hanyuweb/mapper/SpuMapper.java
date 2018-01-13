package cn.hanyuweb.mapper;

import java.util.List;
import java.util.Map;

import cn.hanyuweb.bean.T_MALL_PRODUCT;
import cn.hanyuweb.bean.T_MALL_PRODUCT_IMAGE;

public interface SpuMapper {

	void insert_product(T_MALL_PRODUCT product);

	void insert_product_image(Map<Object, Object> paramMap);

	List<T_MALL_PRODUCT> select_product(T_MALL_PRODUCT product);

	T_MALL_PRODUCT selectProductById(Integer id);

	List<T_MALL_PRODUCT_IMAGE> select_product_image_by_shp_id(Integer id);

	void update_product(T_MALL_PRODUCT product);

	void delete_product_image_by_shp_id(int id);
}
