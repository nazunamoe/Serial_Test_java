package Function;

import jssc.SerialPort;
import jssc.SerialPortList;
import jssc.SerialPortException;

public class Main {
	public static void main(String[] args){
		String[] portNames = SerialPortList.getPortNames();
		for(int i=0; i<portNames.length; i++) {
			System.out.println(portNames[i]);
		}
	SerialPort serialPort = new SerialPort("COM1");
	try {
		serialPort.openPort();
		serialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
		serialPort.writeString("(AN)");
		byte[] buffer = serialPort.readBytes(10);
		serialPort.closePort();
	}catch(SerialPortException ex) {
		System.out.println(ex);
		}
	}
}
