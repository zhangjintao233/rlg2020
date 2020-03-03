package com.itdr.service.impl;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.mapper.GwcMapper;
import com.itdr.mapper.ProductMapper;
import com.itdr.pojo.Gwc;
import com.itdr.pojo.Product;
import com.itdr.pojo.Users;
import com.itdr.pojo.vo.GwcProductVo;
import com.itdr.pojo.vo.GwcVo;
import com.itdr.service.GwcService;
import com.itdr.utils.BigDecimalUtils;
import com.itdr.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class GwcServiceImpl implements GwcService {

    @Autowired
    GwcMapper gwcMapper;

    @Autowired
    ProductMapper productMapper;

    // 获取GwcVo对象
    protected GwcVo getGwcVo(List<Gwc> gwcList) {
        // 封装购物车
        List<GwcProductVo> gwcProductVos = new ArrayList<>();
        boolean bol = true;
        BigDecimal gwcPrice = new BigDecimal("0");
        for (Gwc gwc : gwcList) {
            Product product = productMapper.selectById(gwc.getPid());
            if (product != null) {
                GwcProductVo gwcProductVo = ObjectUtils.gwcProduct(gwc, product);
                gwcProductVos.add(gwcProductVo);

                // 计算总价，只计算选中的
                if (gwcProductVo.getChecked() == 1) {
                    gwcPrice = BigDecimalUtils.add(gwcPrice.doubleValue(), gwcProductVo.getProductTotalPrice().doubleValue());
                }
            }

            if (gwc.getChecked() != 1) {
                bol = false;
            }
        }

        GwcVo gwcVo = ObjectUtils.TuGwcVo(gwcProductVos, bol, gwcPrice);

        return gwcVo;
    }

    // 获取购物车列表
    private ServerResponse<List<Gwc>> gwcGwcList(Users user) {
        List<Gwc> gwcList = gwcMapper.selectByUserId(user.getId());
        if (gwcList.size() == 0) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.GwcEnum.NULL_USER.getDesc()
            );
        }
        return ServerResponse.successRS(gwcList);
    }

    // 判断商品是否在售
    private ServerResponse<Product> ifProduct(Integer productId){
        Product product = productMapper.selectById(productId);
        if (product == null || product.getStatus() != 1) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.GwcEnum.NULL_PRODUCT.getDesc());
        }
        return ServerResponse.successRS(product);
    }


    // 封装好的GwcVo对象
    // 查询登录用户购物车信息
    @Override
    public ServerResponse list(Users user) {
        ServerResponse<List<Gwc>> gwcList = gwcGwcList(user);
        if (!gwcList.isSuccess()) {
            return gwcList;
        }

        return ServerResponse.successRS(getGwcVo(gwcList.getData()));
    }




    @Override
    public ServerResponse add(Integer productId, Integer count, Integer type, Users user) {
        // 判断参数是否合法
        if (productId == null || productId < 0) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc());
        }
        if (count == null || count < 0) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc());
        }

        // 判断商品是否存在或在售 1代表在售
        ServerResponse<Product> ifProduct = ifProduct(productId);
        if (!ifProduct.isSuccess()){
            return ifProduct;
        }
        // 判断是否查出库存
        if (ifProduct.getData().getCount() < count) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.GwcEnum.GUO_PRODUCT.getDesc());
        }

        // 向购物车增加或更新一条数据
        Gwc g = new Gwc();
        g.setUid(user.getId());
        g.setPid(productId);
        g.setQuantiy(count);

        Gwc gwc = gwcMapper.selectByuseridAndProductId(user.getId(), productId);
        if (gwc == null) {
            int insert = gwcMapper.insert(g);
            if (insert <= 0) {
                return ServerResponse.defeatedRS(
                        ConstCode.DEFAULT_FAIL,
                        ConstCode.GwcEnum.FAIL_PRODUCT.getDesc());
            }
        } else {
            // 根据type决定更新的方法
            if (type == ConstCode.GwcEnum.LEI_ONE.getCode()) {
                gwc.setQuantiy(count + gwc.getQuantiy());
            } else if (type == ConstCode.GwcEnum.LEI_TOW.getCode()) {
                gwc.setQuantiy(count);
            }
            int i = gwcMapper.updateByQuantiy(gwc);
            if (i <= 0) {
                return ServerResponse.defeatedRS(
                        ConstCode.DEFAULT_FAIL,
                        ConstCode.GwcEnum.FAIL_PRODUCT.getDesc());
            }
        }


        // 封装好的GwcVo对象
        // 查询登录用户购物车信息
        return ServerResponse.successRS(list(user));
    }


    @Override
    public ServerResponse update(Integer productId, Integer count, Integer type, Users user) {
        // 判断参数是否合法
        if (productId == null || productId < 0) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc());
        }
        if (count == null || count < 0) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc());
        }

        // 判断商品是否存在或在售 1代表在售
        ServerResponse<Product> ifProduct = ifProduct(productId);
        if (!ifProduct.isSuccess()){
            return ifProduct;
        }

        // 查询要更新的数据
        Gwc gwc = gwcMapper.selectByuseridAndProductId(user.getId(), productId);
        // 根据type决定更新的方法
        if (type == ConstCode.GwcEnum.LEI_ONE.getCode()) {
            gwc.setQuantiy(count + gwc.getQuantiy());
        } else if (type == ConstCode.GwcEnum.LEI_TOW.getCode()) {
            gwc.setQuantiy(count);
        }
        int i = gwcMapper.updateByQuantiy(gwc);
        if (i <= 0) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.GwcEnum.FAIL_PRODUCT.getDesc());
        }

        // 封装好的GwcVo对象
        // 查询登录用户购物车信息
        return ServerResponse.successRS(list(user));
    }


    @Override
    public ServerResponse delete(Integer productId, Users user) {
        // 判断参数是否合法
        if (productId == null || productId < 0) {
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc());
        }

        int i = gwcMapper.deleteByUserIdAndProductId(user.getId(),productId);
        if (i<=0){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.GwcEnum.FAIL_DELETE.getDesc()
            );
        }


        return ServerResponse.successRS(list(user));
    }


    @Override
    public ServerResponse selectUserProduct(Users user) {
        List<Gwc> list = gwcMapper.selectByUserId(user.getId());
        Integer num = 0;
        for (Gwc gwc : list) {
            num += gwc.getQuantiy();
        }

        return ServerResponse.successRS(num);
    }


    @Override
    public ServerResponse checked(Integer productId, Integer type, Users user) {
        // 判断参数是否合法
        if (productId != null && productId < 0){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc());
        }
        if (type != null && type < 0){
            return ServerResponse.defeatedRS(
                    ConstCode.DEFAULT_FAIL,
                    ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc());
        }


        int i = gwcMapper.updateByChecked(user.getId(),productId,type);


        return list(user);
    }


    @Override
    public ServerResponse over(Users user) {
        List<Gwc> gwcs = gwcMapper.selectByUserId(user.getId());
        if (gwcs == null){
            return ServerResponse.defeatedRS(ConstCode.GwcEnum.NULL_USER.getDesc());
        }

        boolean bo = false;
        for (Gwc g : gwcs){
            if (g.getChecked() == 1){
                bo = true;
            }
        }
        if (!bo){
            return ServerResponse.defeatedRS(ConstCode.GwcEnum.NO_PRODUCT.getDesc());
        }
        return ServerResponse.successRS(true);
    }



}
