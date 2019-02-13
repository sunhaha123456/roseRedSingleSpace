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
 4、redis中用户信息存储结构：
     user_id_xxx:{token:xxxxxx, userState:x}
     例如：user_id_1:{"token":"a688c8941dcb410894c3504a9dd74514f94d26fe2cf747a69945f5eaee517d57","userState":0}
     如果某一用户登录后，再次登录时，则会将上一个用户给顶下来，
     即：一个账号，只能有一个用户同时在线