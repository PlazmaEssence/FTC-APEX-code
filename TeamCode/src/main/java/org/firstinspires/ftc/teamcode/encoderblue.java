package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Robot: MATH", group="Robot")
public class encoderblue extends LinearOpMode {
    private DcMotor intakeMotor;

    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor bklDrive = null;
    private DcMotor bkrDrive = null;
    private DcMotor ftlDrive = null;
    private DcMotor ftrDrive = null;
    private double intakePower = 0;

    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // No External Gearing.
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.8;
    static final double TURN_SPEED = 0.9;


    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.5;

    static final double INTAKE_SPEED = 1.0;
    static final double leftServoSpeed = 1.0;
    static final double rightServoSpeed = 1.0;

    //    @Override
    public void runOpMode() {

        bklDrive = hardwareMap.get(DcMotor.class, "bLD");
        bkrDrive = hardwareMap.get(DcMotor.class, "bRD");
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        servoLeft = hardwareMap.get(CRServo.class, "sL");
        servoRight = hardwareMap.get(CRServo.class, "sR");

        bklDrive.setDirection(DcMotor.Direction.FORWARD);
        bkrDrive.setDirection(DcMotor.Direction.REVERSE);

        bklDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        bklDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bkrDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bklDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bkrDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Starting at " + String.valueOf(bklDrive.getCurrentPosition()), "" + String.valueOf(bkrDrive.getCurrentPosition()));
        telemetry.update();

        while (opModeIsActive()) {
            telemetry.addData("mortor position", bklDrive.getCurrentPosition());
            telemetry.addData("mortor position", bkrDrive.getCurrentPosition());
            telemetry.update();

            // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
            // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
            // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
            bklDrive.setDirection(DcMotor.Direction.FORWARD);
            bkrDrive.setDirection(DcMotor.Direction.REVERSE);
            bklDrive.setDirection(DcMotor.Direction.FORWARD);
            bkrDrive.setDirection(DcMotor.Direction.REVERSE);


            // Send telemetry message to signify robot waiting;
            telemetry.addData("Status", "Ready to run");    //
            telemetry.update();


            // Wait for the game to start (driver presses START)
            waitForStart();

            // Step through each leg of the path, ensuring that the OpMode has not been stopped along the way.

            // Step 1:  Drive forward for 3 seconds


        }

        double bkLeftPower;
        double bkRightPower;
        double ftLeftPower;
        double ftRightPower;

        double drive = -gamepad1.left_stick_y;
        double strafe =  gamepad1.left_stick_x ;
        double rotate =  gamepad1.right_stick_x;

        ftLeftPower = Range.clip(drive - strafe - rotate, -0.9, 0.9);
        ftRightPower = Range.clip(drive + strafe + rotate, -0.9, 0.9);
        bkLeftPower = Range.clip(drive + strafe - rotate, -0.9, 0.9);
        bkRightPower = Range.clip(drive - strafe + rotate, -0.9, 0.9);

        // Tank Mode uses one stick to control each wheel.
        // - requires no math,but it is hard to drive forward slowly and keep straight.
        // leftPower  = -gamepad1.left_stick_y;
        // rightPower = -gamepad1.right_stick_y;

        // Send calculated power to wheels
        bklDrive.setPower(bkLeftPower);
        bkrDrive.setPower(bkRightPower);
        ftlDrive.setPower(ftLeftPower);
        ftrDrive.setPower(ftRightPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "front left (%.2f), front right (%.2f), back left (%.2f), back right (%.2f)", ftLeftPower, ftLeftPower,bkLeftPower,bkRightPower);
        telemetry.update();





    }

    public void encoderSpin(double speed,double speed1,
                           double ticks,double ticks1,
                           double timeoutS) {
        int newTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = bklDrive.getCurrentPosition() + (int)(ticks);
            bklDrive.setTargetPosition(newTarget);
            newTarget = bkrDrive.getCurrentPosition() + (int)(ticks1);
            bkrDrive.setTargetPosition(newTarget);

            // Turn On RUN_TO_POSITION


            bklDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bkrDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
           bklDrive.setPower(Math.abs(speed));
           ftlDrive.setPower(Math.abs(speed));
           bkrDrive.setPower(Math.abs(speed1));
            ftrDrive.setPower(Math.abs(speed1));


            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (bklDrive.isBusy() || bkrDrive.isBusy())){

                // Display it for the driver.
                telemetry.addData("Running to",  " %7d", newTarget);
                telemetry.addData("Currently at ", bklDrive.getCurrentPosition());
                telemetry.addData("Currently at ", bkrDrive.getCurrentPosition());


                //}

                // Stop all motion;
                //     bklDrive.setPower(0);
                //    bkrDrive.setPower(0);

                // Turn off RUN_TO_POSITION
                bklDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                bkrDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                sleep(250);   // optional pause after each move


            }



        }
    }




    }



