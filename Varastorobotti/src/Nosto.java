import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Nosto implements Behavior{

	private volatile boolean suppressed = false;
	public static boolean paluu = false;

	RegulatedMotor A;
	RegulatedMotor B;
	RegulatedMotor C;

	public static boolean getP(){
		return paluu;
	}

	public Nosto(RegulatedMotor A){
		this.A = A;

	}

	@Override
	public boolean takeControl() {

			return NaviMeno.getN();

		}

	@Override
	public void action() {
		suppressed = false;
		System.out.println("NOSTON YRITYS");
		A.setSpeed(100);
		A.backward();
		paluu = true;
		Delay.msDelay(3000);
		A.stop();


	}

	@Override
	public void suppress() {
		suppressed = true;

	}
}
