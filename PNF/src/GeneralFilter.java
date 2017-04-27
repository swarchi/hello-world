/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description
 *      CommonForFilter 인터페이스를 구현한 추상클래스. 모든 Filter는 본 Filter를 상속받아 구현하게 된다. 
 */
public abstract class GeneralFilter implements CommonForFilter{
    protected PipedInputStream in = new PipedInputStream();
    protected PipedOutputStream out = new PipedOutputStream();
    
/**********Implementation of public methods defined in CommonForFilter interface************/
    
    public void connectOutputTo(CommonForFilter nextFilter) throws IOException {
        out.connect(nextFilter.getPipedInputStream());
    }
    
    public void connectInputTo(CommonForFilter previousFilter) throws IOException {
        in.connect(previousFilter.getPipedOutputStream());
    }
    
    public PipedInputStream getPipedInputStream() { 
        return in; 
    }
    
    public PipedOutputStream getPipedOutputStream() { 
        return out; 
    }

/**********Implementation of public methods defined in Runnable interface************/    

    public void run() {
        try { 
            specificComputationForFilter(); 
        } catch (IOException e) {
            if (e instanceof EOFException) return;
            else System.out.println(e);
        } finally {
            try { 
                out.close(); 
                in.close(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            }
        }
    }

/**********Implementation of protected methods************/    
    
    /**
     * Filter가 작동을 정지하기 전에 Input/Output Stream port를 닫는다.
     */
    protected void closePorts() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }
    
/**********Abstract method that should be implemented************/    
    
    /**
     * 각 Filter의 특수한 기능이 이곳에 기록되며, 이 메소드는 run()에 의해 호출됨으로써 Filter가 기능하게 된다.
     * @throws IOException
     */
    abstract public void specificComputationForFilter() throws IOException;
}
