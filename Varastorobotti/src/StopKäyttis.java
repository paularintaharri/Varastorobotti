
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class StopKäyttis implements Behavior{


		private volatile boolean suppressed = false;
		public static boolean lopeta = false;

		Main main = new Main();

		RegulatedMotor B;
		RegulatedMotor C;
		RegulatedMotor A;
		ServerSocket serveri;
		Socket s;
		DataInputStream in;
		DataOutputStream out;

		

		public StopKäyttis(RegulatedMotor A, RegulatedMotor B, RegulatedMotor C, 
				ServerSocket serveri, Socket s,DataInputStream in, DataOutputStream out){
			this.A = A;
			this.B = B;
			this.C = C;
			this.serveri = serveri;
			this.s = s;
			this.in = in;
			this.out = out;
			
		}


		@Override
		public boolean takeControl() {
			boolean lopeta = false;
			try {
				lopeta = in.readBoolean();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return lopeta;
		}

		@Override
		public void action() {
			suppressed = false;
			System.out.println("STOPKäyttis");
			A.stop();
			B.stop();
			C.stop();
			System.exit(-1);

			while(!suppressed)Thread.yield();
			A.stop();
			B.stop();
			C.stop();
			}



		@Override
		public void suppress() {
			suppressed = true;

		}
}

