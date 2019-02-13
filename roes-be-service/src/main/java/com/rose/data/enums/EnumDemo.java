package com.rose.data.enums;

/**
 * 功能：酒店订单状态 enum
 *      对应 sx_order 表 order_status 字段
 * @author sunpeng
 */
public enum EnumDemo {

    // sx_order 表 order_status 字段，如下：
    // 订单状态:
    //      -8_订单超时失效, -7_确认取消退款失败,-6_拒单取消退款失败,
    //      -5_确认取消退款成功,-4_拒单退款完成,-3_拒单退款中,
    //      -2_确认取消退款中,-1_取消订单,0_预订单,
    //      1_未支付,2_已支付完成,3,已完成,
    //      4_支付失败,5_线上确认完成,
    //      6_商户向运营申请拒单,7_运营拒绝商户拒单申请

    ORDER_OVER_TIME_LOSE_EFFICACY(-8, "订单超时失效"),
    ORDER_CONFIRM_CANCEL_BACK_MONEY_FAIL(-7, "确认取消退款失败"),
    ORDER_REJECT_CANCEL_BACK_MONEY_FAIL(-6, "拒单取消退款失败"),
    ORDER_CONFIRM_BACK_MONEY_SUCCESS(-5, "确认取消退款成功"),
    ORDER_REJECT_BACK_MONEY_COMPLETE(-4, "拒单退款完成"),
    ORDER_REJECT_BACK_MONEY_PROCESSING(-3, "拒单退款中"),
    ORDER_CONFIRM_CANCEL_BACK_MONEY_PROCESSING(-2, "确认取消退款中"),
    ORDER_CANCEL(-1, "取消订单"),
    ORDER_RESERVE(0, "预订单"),
    ORDER_NOT_PAY(1, "未支付"),
    ORDER_ALREADY_PAID_COMPLETE(2, "已支付完成"),
    ORDER_ALREADY_COMPLETE(3, "已完成"),
    ORDER_PAY_FAIL(4, "支付失败"),
    ORDER_ONLINE_CONFIRM_COMPLETE(5, "线上确认完成"),
    ORDER_MERCH_TO_OPERATION_APPLY_REJECT(6, "商户向运营申请拒单"),
    ORDER_OPERATION_REFUSE_MERCH_REJECT_APPLY(7, "运营拒绝商户拒单申请"),
    ;

    private Integer index;
    private String name;

    EnumDemo(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public static String getName(Integer index) {
        for (EnumDemo c : EnumDemo.values()) {
            if (c.getIndex().equals(index)) {
                return c.name;
            }
        }
        return null;
    }

    public static Integer getIndex(String name) {
        for (EnumDemo c : EnumDemo.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }
}