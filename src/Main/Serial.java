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
			try {
				String receivedData = serialPort.readString(event.getEventValue());
				System.out.println("Received Message : "+receivedData);
				if(receivedData.contains(")")){
					Func.CommandByte = Func.CommandByte.append(receivedData);
	                Func.getCommand(Func.CommandByte.toString());  
					Func.CommandByte = new StringBuffer("");
				}else {
					Func.CommandByte = Func.CommandByte.append(receivedData);
				}
			} catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: " + ex);
			}
		}
	}
}