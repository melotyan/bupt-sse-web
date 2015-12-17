package cn.sse.bupt.common;

import org.springframework.web.servlet.view.JstlView;

import java.io.File;
import java.util.Locale;

/**
 * Created by hao.yan on 2015/12/17.
 */
public class CustomerJspView extends JstlView {

    @Override
    public boolean checkResource(Locale locale) throws Exception {
        File file = new File(this.getServletContext().getRealPath("/")+getUrl());
        return file.exists();//判断该jsp页面是否存在
    }
}
