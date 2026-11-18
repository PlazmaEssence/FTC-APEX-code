package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "DuncanTeleop", group = "Robot")

public class teleopToWin extends LinearOpMode {

    boolean sense = true;
    boolean toggle;
    int count = 0;
double Up = 0;
double Down = 1;
    private Servo sorter1;
    private Servo gate;
    private Servo sorter2;
    private AnalogInput laserAnalog;

    private DcMotor fLD = null;
    private DcMotor fRD = null;
    private DcMotor bLD = null;
    private DcMotor bRD = null;
    private DcMotor pollen = null;
    private DcMotor nectar = null;
    private DcMotor intake = null;
    private static final double MAX_VOLTS = 3.3;
    private static final double MAX_DISTANCE_MM = 1000.0;

    public void runOpMode() {

        tel();
        }























        public void sense(){
            double volts = laserAnalog.getVoltage();
            double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;
            if (distanceMM < 250) {

                if (sense == true) {
                    count += 1;
                    sense = false;
                }
                if (sense == false) ;

            } else {
                sense = true;

            }
        }
        public void movement(){
            if (gamepad2.left_trigger > 0) {
                pollen.setPower(0.8);
                sorter1.setPosition(1);

            } else {
                pollen.setPower(0);
                sorter1.setPosition(0);
            }
            if (gamepad2.right_trigger > 0) {
                nectar.setPower(0.8);
                sorter2.setPosition(1);

            } else {
                nectar.setPower(0);
                sorter2.setPosition(0);
            }
            if (gamepad2.bWasPressed()) {
                toggle = !toggle;
            }
            if (toggle) {
                intake.setPower(0.5);
                gate.setPosition(Up);
            } else {
                intake.setPower(0);}
            gate.setPosition(Down);
            if (count == 4) {

                gate.setPosition(Up);
            } else { gate.setPosition(Down);}
            double volts = laserAnalog.getVoltage();
            double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;
            double backLeftPower;
            double backRightPower;
            double frontLeftPower;
            double frontRightPower;

            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            frontLeftPower = Range.clip(drive + strafe - rotate, -0.9, 0.9);
            frontRightPower = Range.clip(drive - strafe + rotate, -0.9, 0.9);
            backLeftPower = Range.clip(drive - strafe - rotate, -0.9, 0.9);
            backRightPower = Range.clip(drive + strafe + rotate, -0.9, 0.9);

            bLD.setPower(backLeftPower);
            bRD.setPower(backRightPower);
            fRD.setPower(frontLeftPower);
            fLD.setPower(frontRightPower);

            telemetry.addData("Voltage (V)", "%.3f", volts);
            telemetry.addData("Distance (mm)", "%.1f", distanceMM);
            telemetry.addData("Count", count);
            telemetry.update();
            telemetry.addData("count", "");
        }
        public void initStuff() {
            pollen = hardwareMap.get(DcMotor.class, "pollen");
            nectar = hardwareMap.get(DcMotor.class, "nectar");
            intake = hardwareMap.get(DcMotor.class, "intake");
            sorter1 = hardwareMap.get(Servo.class, "sorter1");
            sorter2 = hardwareMap.get(Servo.class, "sorter2");
            gate = hardwareMap.get(Servo.class, "gate");
            laserAnalog = hardwareMap.get(AnalogInput.class, "laserAnalogInput");
            fLD = hardwareMap.get(DcMotor.class, "FLD");
            fRD = hardwareMap.get(DcMotor.class, "FRD");
            bLD = hardwareMap.get(DcMotor.class, "BLD");
            bRD = hardwareMap.get(DcMotor.class, "BRD");
            fLD.setDirection(DcMotor.Direction.FORWARD);
            fRD.setDirection(DcMotor.Direction.REVERSE);
            bLD.setDirection(DcMotor.Direction.FORWARD);
            bRD.setDirection(DcMotor.Direction.REVERSE);
            pollen.setDirection(DcMotor.Direction.FORWARD);
            intake.setDirection(DcMotor.Direction.FORWARD);
            telemetry.addData("Status", "Ready to run");    //
            telemetry.update();
        }
        public void tel(){
            initStuff();
            waitForStart();
            while (opModeIsActive()) {
                sense();
                movement();
            }
        }
    }