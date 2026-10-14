package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name="Robot: Red_flower_hive_down", group="Robot")
public class straferEncoderAutoRed2 extends LinearOpMode {
    //private DcMotor intakeMotor;

    private CRServo servoLeft;
    private CRServo servoRight;
    private DcMotor bLDrive = null;
    private DcMotor bRDrive = null;
    private DcMotor fLDrive = null;
    private DcMotor fRDrive = null;
   // private DcMotor shoter1 = null;
  // private DcMotor shoter2 = null;


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


    //    @Override
    public void runOpMode() {

        fLDrive = hardwareMap.get(DcMotor.class, "FLD");
        fRDrive = hardwareMap.get(DcMotor.class, "FLD");
        bLDrive = hardwareMap.get(DcMotor.class, "BLD");
        bRDrive = hardwareMap.get(DcMotor.class, "BRD");
        //  intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        // servoLeft = hardwareMap.get(CRServo.class, "SL");
        // servoRight = hardwareMap.get(CRServo.class, "SR");

        bLDrive.setDirection(DcMotor.Direction.FORWARD);
        bRDrive.setDirection(DcMotor.Direction.REVERSE);
        fLDrive.setDirection(DcMotor.Direction.FORWARD);
        fRDrive.setDirection(DcMotor.Direction.REVERSE);
        //  intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        // shoter.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("status","Ready to run");
telemetry.update();

        waitForStart();




        // intakeMotor.setPower(INTAKE_SPEED);
        // servoLeft.setPower(leftServoSpeed);
        //  servoRight.setPower(rightServoSpeed);
       // sleep(3000);

      //  diveforward(0.2, 267, 9);
        strafe_left(0.2, 538, 9, 50);

        //  zero_motor();

        //  strafe_right(0.5, 538, 9);

        //diveforward(0.9, 267, 9);

        //    turn(0.8, 269, 9);

        //   diveforward(0.9, 1076, 9);

        // shoterDrive(6, 416, 9);


        zero_motor();


    }

    public void zero_motor() {

        bLDrive.setPower(0);
        fRDrive.setPower(0);
        fLDrive.setPower(0);
        bRDrive.setPower(0);
        // intakeMotor.setPower(0);
        //  servoRight.setPower(0);
        //   servoLeft.setPower(0);

    }


    public void diveforward(double speed,
                            double ticks,
                            double timeoutS) {
        //sleep(1000);
  

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


            bLDrive.setMode(RUN_TO_POSITION);
            bRDrive.setMode(RUN_TO_POSITION);
            fLDrive.setMode(RUN_TO_POSITION);
            fRDrive.setMode(RUN_TO_POSITION);
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
                telemetry.addData("Running to", " %7d", newTarget);

                telemetry.addData("mortor position", bLDrive.getCurrentPosition());
                telemetry.addData("mortor position", bRDrive.getCurrentPosition());
                telemetry.addData("mortor position", fLDrive.getCurrentPosition());
                telemetry.addData("mortor position", fRDrive.getCurrentPosition());
                telemetry.update();

            }

//todo motors for the shoter  1620 rpm.

            bLDrive.setPower(0);
            fRDrive.setPower(0);
            fLDrive.setPower(0);
            bRDrive.setPower(0);
            sleep(250);





        }
    }

    public void turn(double speed,
                     double ticks,
                     double timeoutS) {
        int newTarget;
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newTarget = bLDrive.getCurrentPosition() - (int) (ticks);
            bLDrive.setTargetPosition(newTarget);
            newTarget = fRDrive.getCurrentPosition() - (int) (ticks);
            fRDrive.setTargetPosition(newTarget);
            newTarget = bRDrive.getCurrentPosition() + (int) (ticks);
            bRDrive.setTargetPosition(newTarget);
            newTarget = fLDrive.getCurrentPosition() + (int) (ticks);
            fLDrive.setTargetPosition(newTarget);

            // Turn On RUN_TO_POSITION
            bLDrive.setMode(RUN_TO_POSITION);
            bRDrive.setMode(RUN_TO_POSITION);
            fLDrive.setMode(RUN_TO_POSITION);
            fRDrive.setMode(RUN_TO_POSITION);
            runtime.reset();


            // reset the timeout time and start motion.

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
            bLDrive.setPower(0);
            fRDrive.setPower(0);
            fLDrive.setPower(0);
            bRDrive.setPower(0);
            sleep(250);


        }
    }

   /* public void backdrive(double speed,
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
            newTarget = bLDrive.getCurrentPosition() - (int) (ticks);
            bLDrive.setTargetPosition(newTarget);
            newTarget = fRDrive.getCurrentPosition() - (int) (ticks);
            fRDrive.setTargetPosition(newTarget);
            newTarget = bRDrive.getCurrentPosition() - (int) (ticks);
            bRDrive.setTargetPosition(newTarget);
            newTarget = fLDrive.getCurrentPosition() - (int) (ticks);
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

//todo motors for the shoter  1620 rpm.

            //   Turn off RUN_TO_POSITION
            bLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            }
*/


    public void strafe_Right(double speed,
                             double ticks,
                             double timeoutS) {

        bLDrive.setPower(speed);
        bRDrive.setPower(speed);
        fLDrive.setPower(speed);
        fRDrive.setPower(speed);

        int newTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newTarget = fLDrive.getCurrentPosition() - (int) (ticks);
            fLDrive.setTargetPosition(newTarget);
            newTarget = fRDrive.getCurrentPosition() + (int) (ticks);
            fRDrive.setTargetPosition(newTarget);
            newTarget = bRDrive.getCurrentPosition() - (int) (ticks);
            bRDrive.setTargetPosition(newTarget);
            newTarget = bLDrive.getCurrentPosition() + (int) (ticks);
            bLDrive.setTargetPosition(newTarget);


            // Turn On RUN_TO_POSITION


            bLDrive.setMode(RUN_TO_POSITION);
            bRDrive.setMode(RUN_TO_POSITION);
            fLDrive.setMode(RUN_TO_POSITION);
            fRDrive.setMode(RUN_TO_POSITION);
            runtime.reset();


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
                    (bLDrive.isBusy() || fRDrive.isBusy())) {


                // Display it for the driver.
                telemetry.addData("Running to", " %7d", newTarget);

                telemetry.addData("mortor position", bLDrive.getCurrentPosition());
                telemetry.addData("mortor position", bRDrive.getCurrentPosition());
                telemetry.addData("mortor position", fLDrive.getCurrentPosition());
                telemetry.addData("mortor position", fRDrive.getCurrentPosition());
                telemetry.update();

            }

//todo motors for the shoter  1620 rpm.

            //   Turn off RUN_TO_POSITION
            bLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        }
    }

    public void strafe_left(double speed,
                            double ticks,
                            double timeoutS,
                            double strafe) {

        int newTarget;
        if (opModeIsActive()) {
            newTarget = fLDrive.getCurrentPosition() + (int) (ticks);
            fLDrive.setTargetPosition(newTarget);
            newTarget = fRDrive.getCurrentPosition() - (int) (ticks);
            fRDrive.setTargetPosition(newTarget);
            newTarget = bRDrive.getCurrentPosition() + (int) (ticks);
            bRDrive.setTargetPosition(newTarget);
            newTarget = bLDrive.getCurrentPosition() - (int) (ticks);
            bLDrive.setTargetPosition(newTarget);



            bLDrive.setMode(RUN_TO_POSITION);
            bRDrive.setMode(RUN_TO_POSITION);
            fLDrive.setMode(RUN_TO_POSITION);
            fRDrive.setMode(RUN_TO_POSITION);
            runtime.reset();


            // reset the timeout time and start motion.

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
                    (bLDrive.isBusy() || fLDrive.isBusy())) {


                // Display it for the driver.
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("mortor position", bLDrive.getCurrentPosition());
                telemetry.addData("mortor position", bRDrive.getCurrentPosition());
                telemetry.addData("mortor position", fLDrive.getCurrentPosition());
                telemetry.addData("mortor position", fRDrive.getCurrentPosition());
                telemetry.update();

            }


//todo motors for the shoter  1620 rpm.

           zero_motor();


            fLDrive.setPower(0);
            fRDrive.setPower(0);
            bLDrive.setPower(0);
            bRDrive.setPower(0);
            sleep(250);
        }
    }

//todo   public void shoterDrive(double speed,
//                            double ticks,
//                            double timeouts) {
//
//
//  todo      shoter.setPower(speed);
//        int newTarget;
//
// todo       if (opModeIsActive()) {
//        // Determine new target position, and pass to motor controller
//            newTarget = shoter.getCurrentPosition() + (int) (ticks);
//            shoter.setTargetPosition(newTarget);
//
//
// todo           // Turn On RUN_TO_POSITION
//
//
// todo           bLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            bRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            fLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            fRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//
//   todo          // reset the timeout time and start motion.
//            runtime.reset();
//            bLDrive.setPower(Math.abs(speed));
//            bRDrive.setPower(Math.abs(speed));
//            fLDrive.setPower(Math.abs(speed));
//            fRDrive.setPower(Math.abs(speed));
//
//
//    todo         // keep looping while we are still active, and there is time left, and both motors are running.
//            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
//            // its target position, the motion will stop.  This is "safer" in the event that the robot will
//            // always end the motion as soon as possible.
//            // However, if you require that BOTH motors have finished their moves before the robot continues
//            // onto the next step, use (isBusy() || isBusy()) in the loop test.
//
//     todo        while (opModeIsActive() &&
//     todo                runtime.seconds() < timeouts &&
//                    (bLDrive.isBusy() || bRDrive.isBusy() || fRDrive.isBusy() || fLDrive.isBusy() || shoter.isBusy())) {
//
//
//          todo       // Display it for the driver.
//                telemetry.addData("Running to", " %7d", newTarget);
//                telemetry.addData("mortor position", bLDrive.getCurrentPosition());
//                telemetry.addData("mortor position", bRDrive.getCurrentPosition());
//                telemetry.addData("mortor position", fLDrive.getCurrentPosition());
//                telemetry.addData("mortor position", fRDrive.getCurrentPosition());
//                telemetry.update();
//
//            }
//
////todo motors for the shoter  1620 rpm.
//
//           todo  //   Turn off RUN_TO_POSITION
//            bLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            bRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            fLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            fRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            shoter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
 //   }
//}

