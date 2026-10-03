package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Robot: Auto Drive By Time Test", group="Robot")

    public class autonomous_strafer_bot extends LinearOpMode {
    private DcMotor intakeMotor;
    private boolean toggleintakeforward = false;
    private boolean toggleintakereverse = false;
    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private double intakePower = 0;
    private double leftServoPower= 0;
    private double rightServoPower = 0;

    private double driveMotorMaxPower = 1.0;

    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;

    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.get(DcMotor.class, "lD");
        rightDrive = hardwareMap.get(DcMotor.class, "rD");


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

        // Step 1:  Drive forward for 3 seconds
        leftDrive.setPower(FORWARD_SPEED);
        rightDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }/*
        bkLDrive.setPower(0);
        bkRDrive.setPower(0);
        ftRDrive.setPower(0);
        ftLDrive.setPower(0);




        bkLDrive.setPower(TURN_SPEED);
        bkRDrive.setPower(-TURN_SPEED);
        ftLDrive.setPower(TURN_SPEED);
        ftRDrive.setPower(-TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();

*/
        }
    }
