/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.IOException;


/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description
 *      입력값으로 받은 byte들을 아무 기능도 하지 않고 그대로 전송하는 기능을 한다.
 */
public class MiddleFilter extends GeneralFilter{

    @Override
    public void specificComputationForFilter() throws IOException {
        int byte_read;
        
        for(;;) {
            byte_read = in.read();
            if (byte_read == -1) 
                return;
            out.write(byte_read);
        }
    }    

}
