package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BiobuzzTeleopSebastian {

    @TeleOp(name = "biobuzztelopsebastian123", group = "Robot")

    public class biobuzztelopsebastian123 extends LinearOpMode {
        //    todo private DcMotor intakeMotor;
        //    todo private boolean toggle = false;
//    todo private boolean toggle2 = false;
//    todo private CRServo servoLeft;
//    todo private CRServo servoRight;
        private DcMotor fLD = null;
        private DcMotor fRD = null;
        private boolean toggle = false;
        private DcMotor bLD = null;
        private DcMotor bRD = null;
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


            waitForStart();
            while (opModeIsActive()) {
                if (gamepad2.bWasPressed()) {
                    toggle = !toggle;
                    forward_and_back(0.5, 200, 5);
                } else {
                    set_all_motors_zero();
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
    }
}
        