package GUI;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
 
public class Serial {
 
    static class SerialPortReader implements SerialPortEventListener {
    	
    	SerialPort serialPort;
    	
    	public SerialPortReader(SerialPort input) {
    		this.serialPort = input;
    	}
 
        public void serialEvent(SerialPortEvent event) {
            //Object type SerialPortEvent carries information about which event occurred and a value.
            //For example, if the data came a method event.getEventValue() returns us the number of bytes in the input buffer.
        	if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    String receivedData = serialPort.readString(1);
                    System.out.println("Received Message : "+receivedData);
                    Func.test = Func.test+receivedData;
                    if(receivedData.equals(")")){
                        System.out.println(Func.test);
                        Func.test = "";
                    }
                } catch (SerialPortException ex) {
                    System.out.println("Error in receiving string from COM-port: " + ex);
                }
            }
            //If the CTS line status has changed, then the method event.getEventValue() returns 1 if the line is ON and 0 if it is OFF.
            else if(event.isCTS()){
                if(event.getEventValue() == 1){
                    System.out.println("CTS - ON");
                }
                else {
                    System.out.println("CTS - OFF");
                }
            }
            else if(event.isDSR()){
                if(event.getEventValue() == 1){
                    System.out.println("DSR - ON");
                }
                else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    }
}