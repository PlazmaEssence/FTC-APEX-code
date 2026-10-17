package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class BiobuzzTeleopDuncan {

    @TeleOp(name="TeleOpDuncan", group="Robot")

    public class encoderAutoRed1 extends LinearOpMode {
        //    todo private DcMotor intakeMotor;
        //    todo private boolean toggle = false;
//    todo private boolean toggle2 = false;
//    todo private CRServo servoLeft;
//    todo private CRServo servoRight;
        private DcMotor frontLeftDrive = null;
        private DcMotor frontRightDrive = null;
        private boolean toggle = false;
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

//        frontLeftDrive.setPower(FORWARD_SPEED);
//        frontRightDrive.setPower(FORWARD_SPEED);
//        backRightDrive.setPower(FORWARD_SPEED);
//        backLeftDrive.setPower(FORWARD_SPEED);
            frontLeftDrive = hardwareMap.get(DcMotor.class, "FLD");
            frontRightDrive = hardwareMap.get(DcMotor.class, "FRD");
            backLeftDrive = hardwareMap.get(DcMotor.class, "BLD");
            backRightDrive = hardwareMap.get(DcMotor.class, "BRD");
//       todo intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
//        servoLeft = hardwareMap.get(CRServo.class, "sL");
//        servoRight = hardwareMap.get(CRServo.class, "sR");

            frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
            frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
            backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
            backRightDrive.setDirection(DcMotor.Direction.REVERSE);


            // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
            // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
            // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
            // Send telemetry message to signify robot waiting;
            telemetry.addData("Status", "Ready to run");    //
            telemetry.update();


            waitForStart();
            while (opModeIsActive()) {
                if (gamepad2.bWasPressed()) {
                    toggle = !toggle;
                    forward_and_back(0.5, 200, 5);
                } else {
                    frontLeftDrive.setPower(0);
                    frontRightDrive.setPower(0);
                    backRightDrive.setPower(0);
                    backLeftDrive.setPower(0);
                }
                double drive = -gamepad1.left_stick_y;
                double strafe = gamepad1.left_stick_x;
                double rotate = gamepad1.right_stick_x;

            }
        }

        public void forward_and_back(double speed,
                            double ticks,
                            double timeoutS) {
            int newTarget;
            //DcMotor.RunMode runToPosition = RUN_TO_POSITION;
            if (opModeIsActive()) {
                newTarget = frontLeftDrive.getCurrentPosition() - (int) (ticks);
                frontLeftDrive.setTargetPosition(newTarget);
                newTarget = frontRightDrive.getCurrentPosition() - (int) (ticks);
                frontRightDrive.setTargetPosition(newTarget);
                newTarget = backLeftDrive.getCurrentPosition() - (int) (ticks);
                backLeftDrive.setTargetPosition(newTarget);
                newTarget = backRightDrive.getCurrentPosition() - (int) (ticks);
                backRightDrive.setTargetPosition(newTarget);

                newTarget = frontLeftDrive.getCurrentPosition() + (int) (ticks);
                frontLeftDrive.setTargetPosition(newTarget);
                newTarget = frontRightDrive.getCurrentPosition() + (int) (ticks);
                frontRightDrive.setTargetPosition(newTarget);
                newTarget = backLeftDrive.getCurrentPosition() + (int) (ticks);
                backLeftDrive.setTargetPosition(newTarget);
                newTarget = backRightDrive.getCurrentPosition() + (int) (ticks);
                backRightDrive.setTargetPosition(newTarget);


                frontLeftDrive.setMode(RUN_TO_POSITION);
                frontRightDrive.setMode(RUN_TO_POSITION);
                backLeftDrive.setMode(RUN_TO_POSITION);
                backRightDrive.setMode(RUN_TO_POSITION);
                runtime.reset();
                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (frontLeftDrive.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", frontLeftDrive.getCurrentPosition());
                    telemetry.update();


                }
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                backLeftDrive.setPower(0);
                backRightDrive.setPower(0);
                sleep(250);
            }}}}
