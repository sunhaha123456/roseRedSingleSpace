============================= 一套使用session 机制的框架 =============================
1、
    使用 BaseRepository 时，入参需要是 params.toArray() 形式
2、现阶段，入参 list 形式时，demo例子：
    @Override
    public List<SxOrderCouponPackage> findByUserIdAndOrderNos(List<String> orderNoList, String userId) {
        StringBuilder sql = new StringBuilder();
        List<String> params = new ArrayList();
        sql.append(" select a.order_no orderNo, a.order_name orderName, a.order_status orderStatus, b.img ");
        sql.append(" from sx_order_coupon_package a join sx_coupon_package b on a.product_id = b.id ");
        sql.append(" where a.user_id = ? ");
        params.add(userId);
        if (orderNoList != null && orderNoList.size() > 0) {
            sql.append(" and a.order_no in ( ");
            for (String orderNo : orderNoList) {
                sql.append("?").append(",");
                params.add(orderNo);
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(" ) ");
        }
        return queryList(sql.toString(), SxOrderCouponPackage.class, params.toArray());
    }
 3、直接从配置文件读取值
     @Value("${rose.db.user}")
     private String dbUser;
 4、系统不支持单点登录
