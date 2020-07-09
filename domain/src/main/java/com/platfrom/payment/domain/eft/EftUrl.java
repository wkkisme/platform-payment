package com.platfrom.payment.domain.eft;

public interface EftUrl {

    String URL = "http://118.140.3.194:8081/eopg_testing_env";

    String CREATE_ORDER = URL + "/ForexTradeRecetion.jsp";

    String QUERY_ORDER = URL + "/ForexCheckQuery";

    String REFUND_ORDER = URL + "/ForexRefundRecetion";

    String QUERY_REFUND_ORDER = URL + "/ForexCheckRefund";


}
