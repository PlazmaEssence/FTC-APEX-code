package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Robot: MATH", group="Robot")
public class straferEncoderAutoRed2 extends LinearOpMode {
    private DcMotor intakeMotor;

    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor bLDrive = null;
    private DcMotor bRDrive = null;
    private DcMotor fLDrive = null;
    private DcMotor fRDrive = null;
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

        fLDrive = hardwareMap.get(DcMotor.class, "FLd");
        fRDrive = hardwareMap.get(DcMotor.class, "FLd");
        bLDrive = hardwareMap.get(DcMotor.class, "BLD");
        bRDrive = hardwareMap.get(DcMotor.class, "BRD");
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        servoLeft = hardwareMap.get(CRServo.class, "SL");
        servoRight = hardwareMap.get(CRServo.class, "SR");

        bLDrive.setDirection(DcMotor.Direction.FORWARD);
        bRDrive.setDirection(DcMotor.Direction.REVERSE);
        fLDrive.setDirection(DcMotor.Direction.FORWARD);
        fRDrive.setDirection(DcMotor.Direction.REVERSE);

        bLDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bRDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fLDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fRDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        bLDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bRDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fLDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fRDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset


        while (opModeIsActive()) {
            telemetry.addData("mortor position", bLDrive.getCurrentPosition());
            telemetry.addData("mortor position", bRDrive.getCurrentPosition());
            telemetry.addData("mortor position", fLDrive.getCurrentPosition());
            telemetry.addData("mortor position", fRDrive.getCurrentPosition());
            telemetry.update();


            // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
            // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
            // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips


            // Send telemetry message to signify robot waiting;
            telemetry.addData("Status", "Ready to run");    //
            telemetry.update();


            // Wait for the game to start (driver presses START)
            waitForStart();

            // Step through each leg of the path, ensuring that the OpMode has not been stopped along the way.

            // Step 1:  Drive forward for 3 seconds


        }


        intakeMotor.setPower(INTAKE_SPEED);
        servoLeft.setPower(leftServoSpeed);
        servoRight.setPower(rightServoSpeed);

        sleep((long) (10000 * time));

        diveforward(0.9, 267, 9);

        turn(0.8, 269, 9);

        diveforward(0.9, 1076, 9);

        zero_morter();


    }

    public void zero_morter() {

        bLDrive.setPower(0);
        fRDrive.setPower(0);
        fLDrive.setPower(0);
        bRDrive.setPower(0);
        intakeMotor.setPower(0);
        servoRight.setPower(0);
        servoLeft.setPower(0);

    }


    public void diveforward(double speed,
                            double ticks,
                            double timeoutS) {
        sleep((long) (1000 * time));
        zero_morter();
        bLDrive.setPower(speed);
        bRDrive.setPower(speed);
        fLDrive.setPower(speed);
        fRDrive.setPower(speed);

        int newTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = bLDrive.getCurrentPosition() + (int) (ticks);
            bLDrive.setTargetPosition(newTarget);
            newTarget = fRDrive.getCurrentPosition() + (int) (ticks);
            fRDrive.setTargetPosition(newTarget);
            newTarget = bRDrive.getCurrentPosition() + (int) (ticks);
            bRDrive.setTargetPosition(newTarget);
            newTarget = fLDrive.getCurrentPosition() + (int) (ticks);
            fLDrive.setTargetPosition(newTarget);

            // Turn On RUN_TO_POSITION


            bLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            bLDrive.setPower(Math.abs(speed));
            bRDrive.setPower(Math.abs(speed));
            fLDrive.setPower(Math.abs(speed));
            fRDrive.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.

            while (opModeIsActive() &&
                    runtime.seconds() < timeoutS &&
                    (bLDrive.isBusy() || bRDrive.isBusy() || fRDrive.isBusy() || fLDrive.isBusy())) {


                // Display it for the driver.
                telemetry.addData("Running to", " %7d", newTarget);

                telemetry.addData("mortor position", bLDrive.getCurrentPosition());
                telemetry.addData("mortor position", bRDrive.getCurrentPosition());
                telemetry.addData("mortor position", fLDrive.getCurrentPosition());
                telemetry.addData("mortor position", fRDrive.getCurrentPosition());
                telemetry.update();

            }

//todo moters for the shoter  1620 rpm.

            //   Turn off RUN_TO_POSITION
            bLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        }
    }

    public void turn(double speed,
                     double ticks,
                     double timeoutS) {
        int newTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = bLDrive.getCurrentPosition() + (int) (ticks);
            bLDrive.setTargetPosition(newTarget);
            newTarget = fRDrive.getCurrentPosition() + (int) (ticks);
            fRDrive.setTargetPosition(newTarget);
            newTarget = bRDrive.getCurrentPosition() + (int) (ticks);
            bRDrive.setTargetPosition(newTarget);
            newTarget = fLDrive.getCurrentPosition() + (int) (ticks);
            fLDrive.setTargetPosition(newTarget);

            // Turn On RUN_TO_POSITION


            // reset the timeout time and start motion.
            runtime.reset();
            bLDrive.setPower(Math.abs(speed));
            bRDrive.setPower(Math.abs(speed));
            fLDrive.setPower(Math.abs(speed));
            fRDrive.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.

            while (opModeIsActive() &&
                    runtime.seconds() < timeoutS &&
                    (bLDrive.isBusy() || bRDrive.isBusy() || fRDrive.isBusy() || fLDrive.isBusy())) {


                // Display it for the driver.
                telemetry.addData("mortor position", bLDrive.getCurrentPosition());
                telemetry.addData("mortor position", bRDrive.getCurrentPosition());
                telemetry.addData("mortor position", fLDrive.getCurrentPosition());
                telemetry.addData("mortor position", fRDrive.getCurrentPosition());
                telemetry.update();
            }


            //   Turn off RUN_TO_POSITION
           /* bLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(250);   // optional pause after each move.*/


        }
    }

    public void backdrive(double speed,
                          double ticks,
                          double timeoutS) {

        bLDrive.setPower(-speed);
        bRDrive.setPower(-speed);
        fLDrive.setPower(-speed);
        fRDrive.setPower(-speed);

        int newTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = bLDrive.getCurrentPosition() + (int) (ticks);
            bLDrive.setTargetPosition(newTarget);
            newTarget = fRDrive.getCurrentPosition() + (int) (ticks);
            fRDrive.setTargetPosition(newTarget);
            newTarget = bRDrive.getCurrentPosition() + (int) (ticks);
            bRDrive.setTargetPosition(newTarget);
            newTarget = fLDrive.getCurrentPosition() + (int) (ticks);
            fLDrive.setTargetPosition(newTarget);

            // Turn On RUN_TO_POSITION


            bLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            bLDrive.setPower(Math.abs(speed));
            bRDrive.setPower(Math.abs(speed));
            fLDrive.setPower(Math.abs(speed));
            fRDrive.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.

            while (opModeIsActive() &&
                    runtime.seconds() < timeoutS &&
                    (bLDrive.isBusy() || bRDrive.isBusy() || fRDrive.isBusy() || fLDrive.isBusy())) {


                // Display it for the driver.
                telemetry.addData("Running to", " %7d", newTarget);

                telemetry.addData("mortor position", bLDrive.getCurrentPosition());
                telemetry.addData("mortor position", bRDrive.getCurrentPosition());
                telemetry.addData("mortor position", fLDrive.getCurrentPosition());
                telemetry.addData("mortor position", fRDrive.getCurrentPosition());
                telemetry.update();

            }

//todo moters for the shoter  1620 rpm.

            //   Turn off RUN_TO_POSITION
            bLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    public void strafe(double speed,
                       double ticks,
                       double timeoutS,
                       double drive,
                       double strafe,
                       double rotate) {

        bLDrive.setPower(speed);
        bRDrive.setPower(speed);
        fLDrive.setPower(speed);
        fRDrive.setPower(speed);

        int newTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newTarget = bLDrive.getCurrentPosition() + (int) (ticks);
            bLDrive.setTargetPosition(newTarget);
            newTarget = fRDrive.getCurrentPosition() + (int) (ticks);
            fRDrive.setTargetPosition(newTarget);
            newTarget = bRDrive.getCurrentPosition() + (int) (ticks);
            bRDrive.setTargetPosition(newTarget);
            newTarget = fLDrive.getCurrentPosition() + (int) (ticks);
            fLDrive.setTargetPosition(newTarget);

            // Turn On RUN_TO_POSITION


            bLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            bLDrive.setPower(Math.abs(speed));
            bRDrive.setPower(Math.abs(speed));
            fLDrive.setPower(Math.abs(speed));
            fRDrive.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.

            while (opModeIsActive() &&
                    runtime.seconds() < timeoutS &&
                    (bLDrive.isBusy() || bRDrive.isBusy() || fRDrive.isBusy() || fLDrive.isBusy())) {


                // Display it for the driver.
                telemetry.addData("Running to", " %7d", newTarget);

                telemetry.addData("mortor position", bLDrive.getCurrentPosition());
                telemetry.addData("mortor position", bRDrive.getCurrentPosition());
                telemetry.addData("mortor position", fLDrive.getCurrentPosition());
                telemetry.addData("mortor position", fRDrive.getCurrentPosition());
                telemetry.update();

            }

//todo moters for the shoter  1620 rpm.

            //   Turn off RUN_TO_POSITION
            bLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        }
    }
}


