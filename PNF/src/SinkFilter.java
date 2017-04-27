/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description
 *      입력값으로 들어온 byte들을 읽어 CS 값만 출력값 파일에 기록하는 클래스.
 */
public class SinkFilter extends GeneralFilter{
    private String filePath;
    
    public SinkFilter(String outputFilePath) {
        this.filePath = outputFilePath;
    }

    @Override
    public void specificComputationForFilter() throws IOException {
        int checkBlank = 4;
        int databyte = 0;  
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean isCS = false;
        
        try {
            FileWriter fw = new FileWriter(this.filePath);
            while(true) {
                while(databyte != '\n' && databyte != -1) {
                    databyte = in.read();
                    if(databyte == ' '){
                        numOfBlank++;
                    }   
                    if(databyte != -1){
                        buffer[idx++] = (byte)databyte;
                    }
                    if(numOfBlank == checkBlank && buffer[idx-3] == 'C' && buffer[idx-2] == 'S'){
                        isCS = true;
                    }
                }
                
                if(isCS == true) {
                    for(int i = 0; i<idx; i++) {
                        fw.write((char)buffer[i]);
                    }
                    isCS = false;
                }
                if(databyte == -1){
                    fw.close();
                    System.out.print( "::Filtering is finished; Output file is created." );
                    return;
                }
                idx = 0;
                numOfBlank = 0;
                databyte = '\0';
            }   
        } catch (Exception e) {
            closePorts();
            e.printStackTrace();
            
        }
    }

}
