package org.firstinspires.ftc.teamcode;


import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="auto_hive_down", group="Robot")


public class auto_hive_down extends LinearOpMode {


    boolean sense = true;
    boolean toggle;


    private AnalogInput laserAnalog;
    private Servo intakeS1 = null;
    int count = 0;


    // Sensor scale: 3.3V corresponds to 1000 mm
    private static final double MAX_VOLTS = 3.3;
    private static final double MAX_DISTANCE_MM = 1000.0;

    double target_distance;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor bLD = null;
    private DcMotor bRD = null;
    private DcMotor fLD = null;
    private DcMotor fRD = null;


    private DcMotor intake = null;


    private Servo pollenSorterServo = null;
    private Servo nectarSorterServo = null;
    double bLP;
    double bRP;
    double fLP;
    double fRP;

    boolean tNS = false;
    boolean tShootP = false;
    boolean toggle3 = false;
    boolean toggle4 = false;
    boolean pShootmanual = false;
    boolean tPS = false;
    double drive;
    double strafe;
    double rotate;

    // boolean toggle = false;
    private DcMotor pollenShooter = null;
    private DcMotor nectarShooter = null;


    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;


    public void runOpMode() {
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();


        intake = hardwareMap.get(DcMotor.class, "intake");
        laserAnalog = hardwareMap.get(AnalogInput.class, "laserAnalogInput");
        intakeS1 = hardwareMap.get(Servo.class, "InS1");
        fLD = hardwareMap.get(DcMotor.class, "fld");
        fRD = hardwareMap.get(DcMotor.class, "frd");
        bLD = hardwareMap.get(DcMotor.class, "bld");
        bRD = hardwareMap.get(DcMotor.class, "brd");
        pollenShooter = hardwareMap.get(DcMotor.class, "pS");
        nectarShooter = hardwareMap.get(DcMotor.class, "nS");
        pollenSorterServo = hardwareMap.get(Servo.class, "sorterP");
        nectarSorterServo = hardwareMap.get(Servo.class, "sorterN");


        fLD.setDirection(DcMotor.Direction.REVERSE);
        fRD.setDirection(DcMotor.Direction.REVERSE);
        bLD.setDirection(DcMotor.Direction.FORWARD);
        bRD.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);
        pollenShooter.setDirection(DcMotor.Direction.REVERSE);
        nectarShooter.setDirection(DcMotor.Direction.FORWARD);

        fLD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bLD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bRD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();


//        double volts = laserAnalog.getVoltage();
//        double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;
//        if (distanceMM < 150)
//            telemetry.addData("count", "");
//        if (distanceMM < 150) {
//
//            if (sense == true) {
//                count += 1;
//                sense = false;
//                //   servo.setPosition(1);
//            }
//            if (sense == false) ;
//
//        } else {
//            sense = true;
//
//        }
//        telemetry.addData("Voltage (V)", "%.3f", volts);
//        telemetry.addData("Distance (mm)", "%.1f", distanceMM);
//        telemetry.addData("Count", count);
//        telemetry.update();

        //  shooter();
        // forward(0.1, 538, 10);





        forward(1,0.3);





    }

    //    public void forward(double speed,
//                        double ticks,
//                        double timeoutS) {
//        int newTarget;
//        int newTarget2;
//        int newTarget3;
//        int newTarget4;
//
//        //DcMotor.RunMode runToPosition = RUN_TO_POSITION;
//        if (opModeIsActive()) {
//            newTarget = fLD.getCurrentPosition() - (int) (ticks);
//            fLD.setTargetPosition(newTarget);
//            newTarget2 = fRD.getCurrentPosition() - (int) (ticks);
//            fRD.setTargetPosition(newTarget2);
//            newTarget3 = bLD.getCurrentPosition() - (int) (ticks);
//            bLD.setTargetPosition(newTarget3);
//            newTarget4 = bRD.getCurrentPosition() - (int) (ticks);
//            bRD.setTargetPosition(newTarget4);
//
//            fLD.setPower(Math.abs(speed));
//            fRD.setPower(Math.abs(speed));
//            bLD.setPower(Math.abs(speed));
//            bRD.setPower(Math.abs(speed));
//
//
//
//
//
//
//            fLD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            fRD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            bLD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            bRD.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            runtime.reset();
//            while (opModeIsActive() &&
//                    (runtime.seconds() < timeoutS) &&
//                    (fLD.isBusy()) && bLD.isBusy()) {
//
//                telemetry.addData("Running to", " %7d", newTarget);
//                telemetry.addData("Running to", " %7d", newTarget2);
//                telemetry.addData("Running to", " %7d", newTarget3);
//                telemetry.addData("Running to", " %7d", newTarget4);
//                telemetry.addData("FLD ", fLD.getCurrentPosition());
//                telemetry.addData("FRD ", fRD.getCurrentPosition());
//                telemetry.addData("BLD ", bLD.getCurrentPosition());
//                telemetry.addData("BRD ", bRD.getCurrentPosition());
//                telemetry.update();
//
//
//            }
//           set_all_motors_zero();
//
//        }
    //  }
    public void forward(double speed,
                        double driveTime) {

        //DcMotor.RunMode runToPosition = RUN_TO_POSITION;
        if (opModeIsActive()) {

            fLD.setPower(Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(Math.abs(speed));

            fLD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fRD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bLD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bRD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            runtime.reset();
            while (opModeIsActive() &&
                    (runtime.seconds() < driveTime)) {
                telemetry.addData("Runtime", runtime.milliseconds());
                telemetry.update();

            }
            set_all_motors_zero();

        }
    }





    public void shooter() {
        pollenShooter.setPower(0.8);
        sleep(1000);
        pollenSorterServo.setPosition(0.2);
        sleep(3000);
        pollenShooter.setPower(0);
        pollenSorterServo.setPosition(0.8);
    }

    public void set_all_motors_zero() {
        fLD.setPower(0);
        fRD.setPower(0);
        bRD.setPower(0);
        bLD.setPower(0);
    }


    public void strafeleft(double speed,double driveTime) {

        if (opModeIsActive()) {

            fLD.setPower(Math.abs(speed));
            fRD.setPower(-Math.abs(speed));
            bLD.setPower(-Math.abs(speed));
            bRD.setPower(Math.abs(speed));

            fLD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fRD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bLD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bRD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            runtime.reset();
            while (opModeIsActive() &&
                    (runtime.seconds() < driveTime)) {
                telemetry.addData("Runtime", runtime.milliseconds());
                telemetry.update();

            }
            set_all_motors_zero();


        }
    }

    public void strafeRight(double speed,double driveTime) {



        if (opModeIsActive()) {

            fLD.setPower(-Math.abs(speed));
            fRD.setPower(Math.abs(speed));
            bLD.setPower(Math.abs(speed));
            bRD.setPower(-Math.abs(speed));

            fLD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fRD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bLD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bRD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            runtime.reset();
            while (opModeIsActive() &&
                    (runtime.seconds() < driveTime)) {
                telemetry.addData("Runtime", runtime.milliseconds());
                telemetry.update();

            }
            set_all_motors_zero();
        }



    }
    public void turn1 ( double speed,
                        double ticks,
                        double timeoutS){
        int newTarget;

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


    }

    public void flower( double speed,
                        double ticks,
                        double timeoutS){
        turn1(0,0,0);
    }

}


