package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Robot: drive", group="Robot")

public class autonomous_starter_bot_code_sure extends LinearOpMode{



    private DcMotor intakeMotor;
    private boolean toggleintakeforward = false;
    private boolean toggleintakereverse = false;
    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    private double leftServoPower= 1;
    private double rightServoPower = 1;

    private double lSP= -1;
    private double rSP = -1;

    private double driveMotorMaxPower = 1.0;

    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED_full = 1;
    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;
    static final double INTAKE_SPEED = 1;
    static final double backward_speed = -0.5;


    public void runOpMode() {

        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        servoLeft = hardwareMap.get(CRServo.class, "sL");
        servoRight = hardwareMap.get(CRServo.class, "sR");
        leftDrive = hardwareMap.get(DcMotor.class, "bLD");
        rightDrive = hardwareMap.get(DcMotor.class, "bRD");


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();

        // Step through each leg of the path, ensuring that the OpMode has not been stopped along the way.
/*
        void forward(double speed, long time) {
            leftDrive.setPower(FORWARD_SPEED);
            rightDrive.setPower(FORWARD_SPEED);
            intakeMotor.setPower(INTAKE_SPEED);
            servoLeft.setPower(leftServoPower);
            servoRight.setPower(rightServoPower);
        }







        void forward(){
*/




        leftDrive.setPower(FORWARD_SPEED_full);
        rightDrive.setPower(FORWARD_SPEED_full);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.4)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);



        leftDrive.setPower(FORWARD_SPEED);
        rightDrive.setPower(FORWARD_SPEED);
        intakeMotor.setPower(INTAKE_SPEED);
        servoLeft.setPower(leftServoPower);
        servoRight.setPower(rightServoPower);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        intakeMotor.setPower(0);
        servoLeft.setPower(0);
        servoRight.setPower(0);


       leftDrive.setPower(backward_speed);
       rightDrive.setPower(backward_speed);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);


        leftDrive.setPower(TURN_SPEED);
        rightDrive.setPower(-TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 4: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        /*
        leftDrive.setPower(FORWARD_SPEED);
        rightDrive.setPower(FORWARD_SPEED);
        intakeMotor.setPower(INTAKE_SPEED);
        servoLeft.setPower(leftServoPower);
        servoRight.setPower(rightServoPower);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
            telemetry.addData("Path", "Leg 5: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        intakeMotor.setPower(0);
        servoLeft.setPower(0);
        servoRight.setPower(0);


        leftDrive.setPower(backward_speed);
        rightDrive.setPower(backward_speed);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 6: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftDrive.setPower(0)
        rightDrive.setPower(0);


        leftDrive.setPower(-TURN_SPEED);
        rightDrive.setPower(TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 7: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);


        leftDrive.setPower(FORWARD_SPEED);
        rightDrive.setPower(FORWARD_SPEED);
        intakeMotor.setPower(INTAKE_SPEED);
        servoLeft.setPower(leftServoPower);
        servoRight.setPower(rightServoPower);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
            telemetry.addData("Path", "Leg 8: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        intakeMotor.setPower(0);
        servoLeft.setPower(0);
        servoRight.setPower(0);

         */
        }
    }
