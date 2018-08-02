package Main;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
 
public class Serial {
 
	static class SerialPortReader implements SerialPortEventListener {
		    	
		private SerialPort serialPort;
		    	
		public SerialPortReader(SerialPort input) {
			this.serialPort = input;
		}
 
		public void serialEvent(SerialPortEvent event) {
			if (event.isRXCHAR() && event.getEventValue() > 0) {
				try {
					String receivedData = serialPort.readString(1);
					System.out.println("Received Message : "+receivedData);
					if(receivedData.equals(")")){
						Func.CommandByte = Func.CommandByte+receivedData;
		                Func.getCommand(Func.CommandByte);  
						Func.CommandByte = "";
					}else {
						Func.CommandByte = Func.CommandByte+receivedData;
					}
				} catch (SerialPortException ex) {
					System.out.println("Error in receiving string from COM-port: " + ex);
				}
			}
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