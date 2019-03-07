package daire_data_upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.math.BigDecimal;

public class Socket_Connection {
	
    static final int PORT = 811;

    String str;

	void connect() throws Exception {

        Fileread fr =new Fileread();
        
        ServerSocket serverSocket = null;
        PrintWriter writer =null;
        
        try {
            serverSocket = new ServerSocket(PORT);
            boolean runFlag = true;
 
            while(runFlag){
 
                System.out.println("–½—ß‚æ‚±‚¹");
                
                Socket socket = serverSocket.accept();
                writer =new PrintWriter(socket.getOutputStream(),true);
                BufferedReader br =new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
                while( (str = br.readLine()) != null ){
                    System.out.println(str);
                    BigDecimal deg=new BigDecimal(fr.fileread());
                    BigDecimal deg_final=deg.setScale(0,BigDecimal.ROUND_HALF_UP);
                    writer.println((int)deg_final.doubleValue());
                    System.out.println((int)deg_final.doubleValue());
                }
 
                if( socket != null){
                    socket.close();
                    socket = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        if( serverSocket != null){
            try {
                serverSocket.close();
                serverSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}