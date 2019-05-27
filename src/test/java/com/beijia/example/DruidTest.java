package com.beijia.example;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

public class DruidTest {
    @Test
    public void test() throws Exception {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAI2VV/0I9mxDGV4cT5XjUwiInxRM+ExDELOfYodu5p/tCrF906UrYAQMEUIyoORdtOoNiX9pkjpxExQnP2sXbYsCAwEAAQ==";
        String password = "iw3harQyIEw/0+4V96GYkXxUMR4EMtDGa2brjNvKqR2fWz8uY0V/Ks086CTECJYskQdd+Jq52u+JWCEtE7x/Yg==";
        String ss = ConfigTools.decrypt(publicKey,password);
//        String sss = ConfigTools.encrypt("QETUOADGJL","QETUOADGJL");
//        System.out.println(sss);
    }
}
