package org.firstinspires.ftc.teamcode;



import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//todo import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="red_2", group="Robot")

public class encoderAutoRed1 extends LinearOpMode {
//    todo private DcMotor intakeMotor;
    //    todo private boolean toggle = false;
//    todo private boolean toggle2 = false;
//    todo private CRServo servoLeft;
//    todo private CRServo servoRight;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
   // final double strafe;

   // {
        //
    //}
//   todo private double intakePower = 0;
//    private double leftServoPower = 0;
//    private double rightServoPower = 0;



    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;
//    todo static final double INTAKE_SPEED = 1.0;
//    todo static final double leftServoSpeed = 1.0;
//    todo static final double rightServoSpeed = 1.0;

    //    @Override
    public void runOpMode() {

        frontLeftDrive.setPower(FORWARD_SPEED);
        frontRightDrive.setPower(FORWARD_SPEED);
        backRightDrive.setPower(FORWARD_SPEED);
        backLeftDrive.setPower(FORWARD_SPEED);
        frontLeftDrive = hardwareMap.get(DcMotor.class, "FLD");
        frontRightDrive = hardwareMap.get(DcMotor.class, "FRD");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "BLD");
        backRightDrive = hardwareMap.get(DcMotor.class, "BRD");
//       todo intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
//        servoLeft = hardwareMap.get(CRServo.class, "sL");
//        servoRight = hardwareMap.get(CRServo.class, "sR");

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
        forward(1, 1000, 10);
        strafe(1,100000,10,50);

        set_all_motors_zero();
//
//        frontLeftDrive.setPower(TURN_SPEED);
//        frontRightDrive.setPower(-TURN_SPEED);
//        backRightDrive.setPower(TURN_SPEED);
//        backLeftDrive.setPower(-TURN_SPEED);
//        runtime.reset();
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
//        todo intakeMotor.setPower(0);
//        todo servoRight.setPower(0);
//        todo servoLeft.setPower(0);

    }


    public void forward(double speed,
                        double ticks,
                        double timeoutS) {
        int newTarget;
        DcMotor.RunMode runToPosition = RUN_TO_POSITION;
        if (opModeIsActive()) {
            newTarget = frontLeftDrive.getCurrentPosition() + (int) (ticks);
            frontLeftDrive.setTargetPosition(newTarget);
            newTarget = frontRightDrive.getCurrentPosition() + (int) (ticks);
            frontRightDrive.setTargetPosition(newTarget);
            newTarget = backLeftDrive.getCurrentPosition() + (int) (ticks);
            backLeftDrive.setTargetPosition(newTarget);
            newTarget = backRightDrive.getCurrentPosition() + (int) (ticks);
            backRightDrive.setTargetPosition(newTarget);
            frontLeftDrive.setMode(runToPosition);
            frontRightDrive.setMode(runToPosition);
            backLeftDrive.setMode(runToPosition);
            backRightDrive.setMode(runToPosition);
            runtime.reset();
            frontLeftDrive.setPower(Math.abs(speed));
            frontRightDrive.setPower(Math.abs(speed));
            backLeftDrive.setPower(Math.abs(speed));
            backRightDrive.setPower(Math.abs(speed));


        }

    }   public void strafe ( double speed,
                             double ticks,
                             double timeoutS,
                             double strafe){
            int newTarget;
            if (opModeIsActive()) {
                newTarget = frontLeftDrive.getCurrentPosition() + (int)(ticks);
                frontLeftDrive.setTargetPosition(newTarget);
                newTarget = frontRightDrive.getCurrentPosition() + (int)(ticks);
                frontRightDrive.setTargetPosition(newTarget);
                newTarget = backLeftDrive.getCurrentPosition() + (int)(ticks);
                backLeftDrive.setTargetPosition(newTarget);
                newTarget = backRightDrive.getCurrentPosition() + (int)(ticks);
                backRightDrive.setTargetPosition(newTarget);
                frontLeftDrive.setMode(RUN_TO_POSITION);
                frontRightDrive.setMode(RUN_TO_POSITION);
                backLeftDrive.setMode(RUN_TO_POSITION);
                backRightDrive.setMode(RUN_TO_POSITION);
                runtime.reset();
                frontLeftDrive.setPower(Math.abs(speed));
                frontRightDrive.setPower(Math.abs(speed));
                backLeftDrive.setPower(Math.abs(speed));
                backRightDrive.setPower(Math.abs(speed));
                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (frontLeftDrive.isBusy())) {
                    telemetry.addData("Running to",  " %7d", newTarget);
                    telemetry.addData("Currently at ", frontLeftDrive.getCurrentPosition());
                                telemetry.update();}
                                 set_all_motors_zero();
                         frontLeftDrive.setPower(0);
                        frontRightDrive.setPower(0);
                    backLeftDrive.setPower(0);
                    backRightDrive.setPower(0);
                    sleep(250);
            }
        }
    }





