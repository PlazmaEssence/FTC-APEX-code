package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Robot: Auto Drive By Time", group="Robot")

    public class autonomous_starter_bot_30_second_pollen_challenge extends LinearOpMode {
    private DcMotor intakeMotor;
    //    private boolean toggle = false;
//    private boolean toggle2 = false;
    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private double intakePower = 0;
    private double leftServoPower = 0;
    private double rightServoPower = 0;


    private ElapsedTime runtime = new ElapsedTime();


    static double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;
    static final double INTAKE_SPEED = 1.0;
    static final double leftServoSpeed = 1.0;
    static final double rightServoSpeed = 1.0;

    //    @Override
    public void runOpMode() {

        frontLeftDrive = hardwareMap.get(DcMotor.class, "FLD");
        frontRightDrive = hardwareMap.get(DcMotor.class, "FRD");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "BLD");
        backRightDrive = hardwareMap.get(DcMotor.class, "BRD");
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        servoLeft = hardwareMap.get(CRServo.class, "sL");
        servoRight = hardwareMap.get(CRServo.class, "sR");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();


        // Wait for the game to start (driver presses START)
        waitForStart();

        // Step through each leg of the path, ensuring that the OpMode has not been stopped along the way.

        // Step 1:  Drive forward for 3 seconds
        double drive = 0;
        double strafe = 0;
        double rotate = 0;
        FORWARD_SPEED = Range.clip(drive - strafe - rotate, -1, 1);
        FORWARD_SPEED = Range.clip(drive + strafe + rotate, -1, 1);
        FORWARD_SPEED = Range.clip(drive + strafe - rotate, -1, 1);
        FORWARD_SPEED = Range.clip(drive - strafe + rotate, -1, 1);

        frontLeftDrive.setPower(FORWARD_SPEED);
        frontRightDrive.setPower(FORWARD_SPEED);
        backRightDrive.setPower(FORWARD_SPEED);
        backLeftDrive.setPower(FORWARD_SPEED);
        intakeMotor.setPower(INTAKE_SPEED);
        servoLeft.setPower(leftServoSpeed);
        servoRight.setPower(rightServoSpeed);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.2)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        set_all_motors_zero();

        frontLeftDrive.setPower(TURN_SPEED);
        frontRightDrive.setPower(-TURN_SPEED);
        backRightDrive.setPower(TURN_SPEED);
        backLeftDrive.setPower(-TURN_SPEED);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        set_all_motors_zero();

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        set_all_motors_zero();


        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();


        }


//                rightDrive.setPower(-TURN_SPEED);
//                rightDrive.setPower(TURN_SPEED);
//                runtime.reset();
//                while (opModeIsActive() && (runtime.seconds() < 2.6)) {
//                    telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
//                    telemetry.update();
//
//                    leftDrive.setPower(FORWARD_SPEED);
//                    rightDrive.setPower(FORWARD_SPEED);
//                    intakeMotor.setPower(INTAKE_SPEED);
//                    servoLeft.setPower(leftServoSpeed);
//                    servoRight.setPower(rightServoSpeed);
//                    runtime.reset();
//                    while (opModeIsActive() && (runtime.seconds() < 7)) {
//                        telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
//                        telemetry.update();
//                    }
//                    set_all_motors_zero();

        //}
        // }
    }

    public void set_all_motors_zero() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        intakeMotor.setPower(0);
        servoRight.setPower(0);
        servoLeft.setPower(0);

    }

}
