package org.firstinspires.ftc.teamcode;



import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name="red_Duncan2", group="Robot")
public class encoderAutoRed2Duncan extends LinearOpMode {
    boolean sense = true;
    boolean toggle;
    int count = 4;

//    private Servo sorter1;
//    private Servo sorter2;
    private AnalogInput laserAnalog;
//    private Servo intakeS1 = null;
//    private Servo intakeS2 = null;


    private DcMotor fLD = null;
    private DcMotor fRD = null;
    private DcMotor bLD = null;
    private DcMotor bRD = null;
//    private DcMotor pollen = null;
//    private DcMotor nectar = null;
//    private DcMotor intake = null;
//
    private ElapsedTime runtime = new ElapsedTime();
    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;
    private static final double MAX_VOLTS = 3.3;
    private static final double MAX_DISTANCE_MM = 1000.0;

    public void runOpMode() {
//        pollen = hardwareMap.get(DcMotor.class, "pollen");
//        nectar = hardwareMap.get(DcMotor.class, "nectar");
//        intake = hardwareMap.get(DcMotor.class, "intake");
//        sorter1 = hardwareMap.get(Servo.class, "sorter1");
//        sorter2 = hardwareMap.get(Servo.class, "sorter2");
        laserAnalog = hardwareMap.get(AnalogInput.class, "laserAnalogInput");
        fLD = hardwareMap.get(DcMotor.class, "fld");
        fRD = hardwareMap.get(DcMotor.class, "frd");
        bLD = hardwareMap.get(DcMotor.class, "bld");
        bRD = hardwareMap.get(DcMotor.class, "brd");
//        intakeS1 = hardwareMap.get(Servo.class, "InS1");
//        intakeS2 = hardwareMap.get(Servo.class, "InS2");
        fLD.setDirection(DcMotor.Direction.FORWARD);
        fRD.setDirection(DcMotor.Direction.REVERSE);
        bLD.setDirection(DcMotor.Direction.FORWARD);
        bRD.setDirection(DcMotor.Direction.REVERSE);
//        pollen.setDirection(DcMotor.Direction.FORWARD);
//        intakeS1.setDirection(Servo.Direction.REVERSE);
//        intakeS2.setDirection(Servo.Direction.REVERSE);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        waitForStart();


        double volts = laserAnalog.getVoltage();
        double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;
        if (distanceMM < 150)
            telemetry.addData("count", "");
        if (distanceMM < 150)
        {

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
//
//            intakeS1.setPosition(0.2);
//            intakeS2.setPosition(0.2);
//            intake.setPower(0);
        }
        telemetry.addData("Voltage (V)", "%.3f", volts);
        telemetry.addData("Distance (mm)", "%.1f", distanceMM);
        telemetry.addData("Count",count);
        telemetry.update();
     sleep(500);
     set_all_motors_zero();
        strafeLeft(0.5, 1000, 10, 50);
        turn(1, 1000, 1);
  //      intake.setPower(1);
        forward(1 ,500 , 1);
        sleep(1000);
  //      intake.setPower(0);
        forward(1 ,-500 , 1);
        turn(1, -1000, 1);
        strafeRight(0.5, 1000, 10, 50);
  //      pollen.setPower(1);
        sleep(500);
        set_all_motors_zero();
        strafeLeft(0.5, 1000, 10, 50);
        forward(0.5, 4000, 10);
        strafeLeft(0.5, 750, 10, 50);
        set_all_motors_zero();
    }

    public void set_all_motors_zero() {
        fLD.setPower(0);
        fRD.setPower(0);
        bRD.setPower(0);
        bLD.setPower(0);
    }
    public void forward(double speed,
                        double ticks,
                        double timeoutS) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = fLD.getCurrentPosition() - (int) (ticks);
            fLD.setTargetPosition(newTarget);
            newTarget = fRD.getCurrentPosition() - (int) (ticks);
            fRD.setTargetPosition(newTarget);
            newTarget = bLD.getCurrentPosition() - (int) (ticks);
            bLD.setTargetPosition(newTarget);
            newTarget = bRD.getCurrentPosition() - (int) (ticks);
            bRD.setTargetPosition(newTarget);
            fLD.setMode(RUN_TO_POSITION);
            fRD.setMode(RUN_TO_POSITION);
            bLD.setMode(RUN_TO_POSITION);
            bRD.setMode(RUN_TO_POSITION);
            runtime.reset();
            fLD.setPower(Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(Math.abs(speed));
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fLD.isBusy())) {
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", fLD.getCurrentPosition());
                telemetry.update();
            }
            fLD.setPower(0);
            fRD.setPower(0);
            bLD.setPower(0);
            bRD.setPower(0);
            sleep(250);
        }
    }
    public void strafeLeft(double speed,
                           double ticks,
                           double timeoutS,
                           double strafe) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = fLD.getCurrentPosition() + (int) (ticks);
            fLD.setTargetPosition(newTarget);
            newTarget = fRD.getCurrentPosition() - (int) (ticks);
            fRD.setTargetPosition(newTarget);
            newTarget = bLD.getCurrentPosition() - (int) (ticks);
            bLD.setTargetPosition(newTarget);
            newTarget = bRD.getCurrentPosition() + (int) (ticks);
            bRD.setTargetPosition(newTarget);

            fLD.setMode(RUN_TO_POSITION);
            fRD.setMode(RUN_TO_POSITION);
            bLD.setMode(RUN_TO_POSITION);
            bRD.setMode(RUN_TO_POSITION);
            runtime.reset();

            fLD.setPower(Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fLD.isBusy())) {
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", fLD.getCurrentPosition());
                telemetry.update();
            }
            set_all_motors_zero();

            fLD.setPower(0);
            fRD.setPower(0);
            bLD.setPower(0);
            bRD.setPower(0);
            sleep(250);
        }
    }
    public void strafeRight(double speed,
                            double ticks,
                            double timeoutS,
                            double strafe) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = fLD.getCurrentPosition() - (int) (ticks);
            fLD.setTargetPosition(newTarget);
            newTarget = fRD.getCurrentPosition() + (int) (ticks);
            fRD.setTargetPosition(newTarget);
            newTarget = bLD.getCurrentPosition() + (int) (ticks);
            bLD.setTargetPosition(newTarget);
            newTarget = bRD.getCurrentPosition() - (int) (ticks);
            bRD.setTargetPosition(newTarget);

            fLD.setMode(RUN_TO_POSITION);
            fRD.setMode(RUN_TO_POSITION);
            bLD.setMode(RUN_TO_POSITION);
            bRD.setMode(RUN_TO_POSITION);
            runtime.reset();

            fLD.setPower(Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fLD.isBusy())) {
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", fLD.getCurrentPosition());
                telemetry.update();
            }
            set_all_motors_zero();

            fLD.setPower(0);
            fRD.setPower(0);
            bLD.setPower(0);
            bRD.setPower(0);
            sleep(250);
        }
    }
    public void turn(double speed,
                     double ticks,
                     double timeoutS) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = fLD.getCurrentPosition() - (int) (ticks);
            fLD.setTargetPosition(newTarget);
            newTarget = fRD.getCurrentPosition() + (int) (ticks);
            fRD.setTargetPosition(newTarget);
            newTarget = bLD.getCurrentPosition() - (int) (ticks);
            bLD.setTargetPosition(newTarget);
            newTarget = bRD.getCurrentPosition() + (int) (ticks);
            bRD.setTargetPosition(newTarget);

            fLD.setMode(RUN_TO_POSITION);
            fRD.setMode(RUN_TO_POSITION);
            bLD.setMode(RUN_TO_POSITION);
            bRD.setMode(RUN_TO_POSITION);
            runtime.reset();

            fLD.setPower(Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fLD.isBusy())) {
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", fLD.getCurrentPosition());
                telemetry.update();
            }
            set_all_motors_zero();


            fLD.setPower(0);
            fRD.setPower(0);
            bLD.setPower(0);
            bRD.setPower(0);
            sleep(250);
        }
    }
}