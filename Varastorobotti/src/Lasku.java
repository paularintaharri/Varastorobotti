import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Lasku implements Behavior{

	private volatile boolean suppressed = false;
	RegulatedMotor A;
	RegulatedMotor B;
	RegulatedMotor C;


	public Lasku(RegulatedMotor A, 	RegulatedMotor B, RegulatedMotor C){
		this.A = A;
		this.B = B;
		this.C = C;

	}

	@Override
	public boolean takeControl(){

		return NaviPaluu.getL();
		}

	@Override
	public void action() {
		suppressed = false;
		System.out.println("LASKUN YRITYS");
		A.setSpeed(100);
		A.forward();

		Delay.msDelay(3000);
		A.stop();
		B.forward();
		C.forward();
		Delay.msDelay(2000);
		System.exit(-1);
		while(!suppressed)Thread.yield();
		A.close();
		B.close();
		C.close();

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

	}
