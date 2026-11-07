package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class BiobuzzTeleopSebastian {

    @TeleOp(name = "bio_buzzTeleopSebastian", group = "Robot")
    public class BiobuzzTelopSebastian extends LinearOpMode {
        private DcMotor intakeMotor;
        private boolean toggle = false;
//    todo private boolean toggle2 = false;
//    todo private CRServo servoLeft;
//    todo private CRServo servoRight;
 //  todo 3.3 is what we need for the 6000 rmp motor/for ticks.
        private DcMotor fLD = null;
        private DcMotor fRD = null;
        private boolean toggle = false;
        private DcMotor bLD = null;
        private DcMotor bRD = null;

        in

        private CRServo servoLeft;
        private CRServo servoRight;

        private double intakePower = 0;
        private double leftServoPower = 0;
        private double rightServoPower = 0;
        int distanceMM;
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

//        fLD.setPower(FORWARD_SPEED);
//        fRD.setPower(FORWARD_SPEED);
//        bRD.setPower(FORWARD_SPEED);
//        bLD.setPower(FORWARD_SPEED);
            fLD = hardwareMap.get(DcMotor.class, "fLD");
            fRD = hardwareMap.get(DcMotor.class, "FRD");
            bLD = hardwareMap.get(DcMotor.class, "BLD");
            bRD = hardwareMap.get(DcMotor.class, "BRD");
//       todo intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
//        servoLeft = hardwareMap.get(CRServo.class, "sL");
//        servoRight = hardwareMap.get(CRServo.class, "sR");

            fLD.setDirection(DcMotor.Direction.FORWARD);
            fRD.setDirection(DcMotor.Direction.REVERSE);
            bLD.setDirection(DcMotor.Direction.FORWARD);
            bRD.setDirection(DcMotor.Direction.REVERSE);


            // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
            // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
            // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
            // Send telemetry message to signify robot waiting;
            telemetry.addData("Status", "Ready to run");    //
            telemetry.update();

            if (gamepad2.xWasPressed()) {
                toggle = !toggle;
                forward_and_back(1, 1000, 29.9);
            }

            if (gamepad2.a || toggle) {
                intakePower = -1;
                rightServoPower = -1;
                leftServoPower = 1;
            } else {
                intakePower = 0;
                rightServoPower = 0;
                leftServoPower = 0;
            }


            if (gamepad2.bWasPressed()) {
                toggle = !toggle;
                back_laser(0.5, 200, 5);
            } else {
                fLD.setPower(0);
                fLD.setPower(0);
                bRD.setPower(0);
                bLD.setPower(0);
            }

            if (distanceMM < 50) {
                back_laser(0.5, 538, 20);

            } else {
                fLD.setPower(0);
                fLD.setPower(0);
                bRD.setPower(0);
                bLD.setPower(0);
                double backLeftPower;
                double backRightPower;
                double frontLeftPower;
                double frontRightPower;

                double drive = -gamepad1.left_stick_y;
                double strafe = gamepad1.left_stick_x;
                double rotate = gamepad1.right_stick_x;

                frontLeftPower = Range.clip(drive + strafe - rotate, -0.9, 0.9);
                frontRightPower = Range.clip(drive - strafe + rotate, -0.9, 0.9);
                backLeftPower = Range.clip(drive - strafe - rotate, -0.9, 0.9);
                backRightPower = Range.clip(drive + strafe + rotate, -0.9, 0.9);

                bLD.setPower(backLeftPower);
                bRD.setPower(backRightPower);
                fRD.setPower(frontLeftPower);
                fLD.setPower(frontRightPower);


            }
        }

        public void forward_and_back(double speed,
                                     double ticks,
                                     double timeoutS) {
            int newTarget;
            //DcMotor.RunMode runToPosition = RUN_TO_POSITION;
            if (opModeIsActive()) {
                newTarget = fLD.getCurrentPosition() - (int) (ticks);
                fLD.setTargetPosition(newTarget);
                newTarget = fRD.getCurrentPosition() - (int) (ticks);
                fRD.setTargetPosition(newTarget);
                newTarget = bLD.getCurrentPosition() - (int) (ticks);
                bLD.setTargetPosition(newTarget);
                newTarget = bRD.getCurrentPosition() - (int) (ticks);
                bRD.setTargetPosition(newTarget);

                newTarget = fLD.getCurrentPosition() + (int) (ticks);
                fLD.setTargetPosition(newTarget);
                newTarget = fRD.getCurrentPosition() + (int) (ticks);
                fRD.setTargetPosition(newTarget);
                newTarget = bLD.getCurrentPosition() + (int) (ticks);
                bLD.setTargetPosition(newTarget);
                newTarget = bRD.getCurrentPosition() + (int) (ticks);
                bRD.setTargetPosition(newTarget);

                fLD.setPower(Math.abs(speed));
                fRD.setPower(Math.abs(speed));
                bLD.setPower(Math.abs(speed));
                bLD.setPower(Math.abs(speed));


                fLD.setMode(RUN_TO_POSITION);
                fRD.setMode(RUN_TO_POSITION);
                bLD.setMode(RUN_TO_POSITION);
                bRD.setMode(RUN_TO_POSITION);
                runtime.reset();
                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (fLD.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", fLD.getCurrentPosition());
                    telemetry.update();


                }
                fLD.setPower(0);
                fRD.setPower(0);
                bLD.setPower(0);
                bRD.setPower(0);
                sleep(250);
            }
        }


        public void set_all_motors_zero() {
            fLD.setPower(0);
            fRD.setPower(0);
            bRD.setPower(0);
            bLD.setPower(0);
//        todo intakeMotor.setPower(0);
//        todo servoRight.setPower(0);
//        todo servoLeft.setPower(0);

        }

        public void turn(double speed,
                         double ticks,
                         double timeoutS) {
            int newTarget;
            if (opModeIsActive()) {
                newTarget = fLD.getCurrentPosition() - (int) (ticks);
                fLD.setTargetPosition(newTarget);
                newTarget = fRD.getCurrentPosition() + (int) (ticks);
                fRD.setTargetPosition(newTarget);
                newTarget = bLD.getCurrentPosition() - (int) (ticks);
                bLD.setTargetPosition(newTarget);
                newTarget = bRD.getCurrentPosition() + (int) (ticks);
                bRD.setTargetPosition(newTarget);

                fLD.setMode(RUN_TO_POSITION);
                fRD.setMode(RUN_TO_POSITION);
                bLD.setMode(RUN_TO_POSITION);
                bRD.setMode(RUN_TO_POSITION);
                runtime.reset();

                fLD.setPower(Math.abs(speed));
                fRD.setPower(Math.abs(speed));
                bLD.setPower(Math.abs(speed));
                bRD.setPower(Math.abs(speed));

                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (fLD.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", fLD.getCurrentPosition());
                    telemetry.update();
                }
                set_all_motors_zero();
                sleep(250);


            }

        }

        public void back_laser(double speed,
                               double ticks,
                               double timeoutS) {
            int newTarget;
            //DcMotor.RunMode runToPosition = RUN_TO_POSITION;
            if (opModeIsActive()) {
                newTarget = fLD.getCurrentPosition() - (int) (ticks);
                fLD.setTargetPosition(newTarget);
                newTarget = fRD.getCurrentPosition() - (int) (ticks);
                fRD.setTargetPosition(newTarget);
                newTarget = bLD.getCurrentPosition() - (int) (ticks);
                bLD.setTargetPosition(newTarget);
                newTarget = bLD.getCurrentPosition() - (int) (ticks);
                bLD.setTargetPosition(newTarget);


                fLD.setMode(RUN_TO_POSITION);
                fRD.setMode(RUN_TO_POSITION);
                bLD.setMode(RUN_TO_POSITION);
                bLD.setMode(RUN_TO_POSITION);
                runtime.reset();

                fLD.setPower(Math.abs(speed));
                fRD.setPower(Math.abs(speed));
                bLD.setPower(Math.abs(speed));
                bLD.setPower(Math.abs(speed));
                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (fLD.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", fLD.getCurrentPosition());
                    telemetry.update();


                }
                fLD.setPower(0);
                fRD.setPower(0);
                bLD.setPower(0);
                bLD.setPower(0);
                sleep(250);

            }
            fLD.setPower(0);
            fRD.setPower(0);
            bLD.setPower(0);
            bRD.setPower(0);
            sleep(250);
        }
    }
}
        