package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


    @TeleOp(name = "laserCounting")
    public class laserCounting extends LinearOpMode {

        boolean sense;
        boolean toggle;
        private AnalogInput laserAnalog;
        private Servo servo = null;
        int count = 0;
        // Sensor scale: 3.3V corresponds to 1000 mm
        private static final double MAX_VOLTS = 3.3;
        private static final double MAX_DISTANCE_MM = 1000.0;

        double target_distance;

        @Override
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
                    telemetry.addData("f aygi", "hello");


                if (distanceMM < 250) {
                    servo.setPosition(1);
                    count += 1;
                    sense = true;

                    if (sense == true) {
                        count += 1;
                        servo.setPosition(1);
                        if (sense == false);

                    } else {
                        sense = false;
                    }
                }

            // Telemetry
                telemetry.addData("Voltage (V)", "%.3f", volts);
                telemetry.addData("Distance (mm)", "%.1f", distanceMM);
                telemetry.addData("Count",count);
                telemetry.update();
            }
        }
    }

