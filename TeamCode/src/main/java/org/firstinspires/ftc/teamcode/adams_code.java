package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

    @Autonomous(name="Robot: Motor Adam", group="Robot")
    public class adams_code extends LinearOpMode {

        /* Declare OpMode members. */
        private DcMotor leftDrive = null;
        //private DcMotor         rightDrive  = null;
        private DcMotor rightDrive = null;
        private ElapsedTime runtime = new ElapsedTime();


        static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
        static final double DRIVE_GEAR_REDUCTION = 1.0;     // No External Gearing.
        static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
        static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                (WHEEL_DIAMETER_INCHES * 3.1415);
        static final double DRIVE_SPEED = 0.6;
        static final double TURN_SPEED = 0.5;

        //@Override
        public void runOpMode() {

            // Initialize the drive system variables.
            leftDrive = hardwareMap.get(DcMotor.class, "bLD");
            rightDrive = hardwareMap.get(DcMotor.class, "brD");
            // rightDrive = hardwareMap.get(DcMotor.class, "bRD");
//        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
//        servoLeft = hardwareMap.get(CRServo.class, "sL");
//        servoRight = hardwareMap.get(CRServo.class, "sR");

            // To drive forward, most robots need the motor on one side to be reversed,
            // because the axles point in opposite directions.
            // When run, this OpMode should start both motors driving forward.
            // So adjust these two lines based on your first test drive.
            // Note: The settings here assume direct drive on left and right wheels.
            // Gear Reduction or 90 Deg drives may require direction flips
            leftDrive.setDirection(DcMotor.Direction.FORWARD);
            rightDrive.setDirection(DcMotor.Direction.FORWARD);
            leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            // rightDrive.setDirection(DcMotor.Direction.REVERSE);
            rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            // Send telemetry message to indicate successful Encoder reset

            telemetry.addData("Starting at " + String.valueOf(leftDrive.getCurrentPosition())
                    , "" + String.valueOf(leftDrive.getCurrentPosition()));

            telemetry.addData("Starting at " + String.valueOf(rightDrive.getCurrentPosition())
                    , "" + String.valueOf(rightDrive.getCurrentPosition()));
            telemetry.update();

            // Wait for the game to start (driver presses START)
            waitForStart();

            while (opModeIsActive()) {
                telemetry.addData("motor position", leftDrive.getCurrentPosition());
                telemetry.addData("motor position", rightDrive.getCurrentPosition());
                telemetry.update();

                rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


                encoderSpin(1,1, 358 ,358, 20);

                sleep(1000);

                leftDrive.setTargetPosition(0);
                rightDrive.setTargetPosition(0);
            }
        }
                public void encoderSpin(double speed,double speed1,
                double ticks,double ticks1,
                double timeoutS) {
                    int newTarget;

                    // Ensure that the OpMode is still active
                    if (opModeIsActive()) {

                        // Determine new target position, and pass to motor controller
                        newTarget = leftDrive.getCurrentPosition() + (int)(ticks);
                        leftDrive.setTargetPosition(newTarget);
                        newTarget = rightDrive.getCurrentPosition() + (int)(ticks1);
                        rightDrive.setTargetPosition(newTarget);

                        // Turn On RUN_TO_POSITION


                        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        // reset the timeout time and start motion.
                        runtime.reset();
                        leftDrive.setPower(Math.abs(speed));
                        rightDrive.setPower(Math.abs(speed1));


                        // keep looping while we are still active, and there is time left, and both motors are running.
                        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
                        // its target position, the motion will stop.  This is "safer" in the event that the robot will
                        // always end the motion as soon as possible.
                        // However, if you require that BOTH motors have finished their moves before the robot continues
                        // onto the next step, use (isBusy() || isBusy()) in the loop test.
                        while (opModeIsActive() &&
                                (runtime.seconds() < timeoutS) &&
                                (leftDrive.isBusy() || rightDrive.isBusy())){

                            // Display it for the driver.
                            telemetry.addData("Running to",  " %7d", newTarget);
                            telemetry.addData("Currently at ", leftDrive.getCurrentPosition());
                            telemetry.addData("Currently at ", rightDrive.getCurrentPosition());

                                    //           leftDrive.getCurrentPosition()), telemetry.addData("Currently at "+ String.valueOf(rightDrive.getCurrentPosition()),
                                    //   rightDrive.getCurrentPosition();
                                    //    telemetry.update();
                                    //}

                                    // Stop all motion;
                                    //     leftDrive.setPower(0);
                                    //    rightDrive.setPower(0);

                                    // Turn off RUN_TO_POSITION
                                    leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                            sleep(250);   // optional pause after each move.

            }
        }

    }
    }

