package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Push", group="Robot")

public class push extends LinearOpMode {
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
    boolean toggle3 = true;
    boolean toggle4 = false;
    boolean pShootmanual = false;
    boolean tPS = false;
    double drive;

    double ticks;
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



        while (opModeIsActive()) {
        fLD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fRD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bLD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bRD.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            telemetry.addData("Currently at ", fLD.getCurrentPosition());
            telemetry.addData("Currently at ", fRD.getCurrentPosition());
            telemetry.addData("Currently at ", bLD.getCurrentPosition());
            telemetry.addData("Currently at ", bRD.getCurrentPosition());
            telemetry.update();

        }
    }
}

