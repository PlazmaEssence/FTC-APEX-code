package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "laserCountingDuncan")
public class LaserDuncan extends LinearOpMode {

    boolean sense = true;
    boolean toggle;


    private AnalogInput laserAnalog;
    private Servo servo = null;
    int count = 0;


    // Sensor scale: 3.3V corresponds to 1000 mm
    private static final double MAX_VOLTS = 3.3;
    private static final double MAX_DISTANCE_MM = 1000.0;

    double target_distance;


    public void runOpMode() {
        // Map the analog device from the hardware configuration
        laserAnalog = hardwareMap.get(AnalogInput.class, "laserAnalogInput");
        servo = hardwareMap.get(Servo.class, "servo");
        // Wait for PLAY
        waitForStart();
        // Main loop
        while (opModeIsActive()) {
            // Read sensor voltage (0.0–3.3V)
            double volts = laserAnalog.getVoltage();
            // Convert voltage to distance in millimeters (linear mapping)
            double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;
            if (distanceMM < 250)
                telemetry.addData("count", "");


            if (distanceMM < 250) {

                if (sense == true) {
                    count += 1;
                    sense = false;
                    //   servo.setPosition(1);
                }
                if (sense == false);

            } else {
                sense = true;

            }

            if (count == 4) {
                servo.setPosition(1);
                servo.setPosition(0);
            } else {
                servo.setPosition(0);
            }

            // todo if true cant be true again until it has been false.

            // Telemetry
            telemetry.addData("Voltage (V)", "%.3f", volts);
            telemetry.addData("Distance (mm)", "%.1f", distanceMM);
            telemetry.addData("Count",count);
            telemetry.update();
        }
    }
}
