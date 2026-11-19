package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class encoderAutoRed2Testing {
    @Autonomous(name = "red_2Testing", group = "Robot")

    public class encoderAutoRed2Duncan extends LinearOpMode {
        boolean sense = true;
        boolean toggle;
        int count = 0;

        private Servo sorter1;
        private Servo sorter2;
        private AnalogInput laserAnalog;

        private DcMotor fLD = null;
        private DcMotor fRD = null;
        private DcMotor bLD = null;
        private DcMotor bRD = null;
        private DcMotor pollen = null;
        private DcMotor nectar = null;
        private DcMotor intake = null;
        // final double strafe;

        // {
        //
        //}
//   todo private double intakePower = 0;
//    private double leftServoPower = 0;
//    private double rightServoPower = 0;


        private ElapsedTime runtime = new ElapsedTime();

        private static final double MAX_VOLTS = 3.3;
        private static final double MAX_DISTANCE_MM = 1000.0;
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
            pollen = hardwareMap.get(DcMotor.class, "pS");
            nectar = hardwareMap.get(DcMotor.class, "nS");
            intake = hardwareMap.get(DcMotor.class, "intake");
            sorter1 = hardwareMap.get(Servo.class, "sorterP");
            sorter2 = hardwareMap.get(Servo.class, "sorterN");
            laserAnalog = hardwareMap.get(AnalogInput.class, "laserAnalogInput");
            fLD = hardwareMap.get(DcMotor.class, "fld");
            fRD = hardwareMap.get(DcMotor.class, "frd");
            bLD = hardwareMap.get(DcMotor.class, "bld");
            bRD = hardwareMap.get(DcMotor.class, "brd");
//       todo intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
//        servoLeft = hardwareMap.get(CRServo.class, "sL");
//        servoRight = hardwareMap.get(CRServo.class, "sR");

            fLD.setDirection(DcMotor.Direction.REVERSE);
            fRD.setDirection(DcMotor.Direction.FORWARD);
            bLD.setDirection(DcMotor.Direction.FORWARD);
            bRD.setDirection(DcMotor.Direction.FORWARD);
            pollen.setDirection(DcMotor.Direction.FORWARD);
            intake.setDirection(DcMotor.Direction.FORWARD);
            telemetry.addData("Status", "Ready to run");    //
            telemetry.update();
            waitForStart();
            double volts = laserAnalog.getVoltage();
            double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;
                if (distanceMM < 250) {

                    if (sense == true) {
                        count += 1;
                        sense = false;
                    }
                    if (sense == false);

                } else {
                    sense = true;

                }
            telemetry.addData("Voltage (V)", "%.3f", volts);
            telemetry.addData("Distance (mm)", "%.1f", distanceMM);
            telemetry.addData("Count",count);
            telemetry.update();
            telemetry.addData("count", "");

            pollen.setPower(1);
            sleep(5000);
            set_all_motors_zero();
            strafeLeft(0.5, 1000, 10, 50);
            turn(1, 1000, 1);
            intake.setPower(1);
            forward(1 ,500 , 1);
            sleep(1000);
            intake.setPower(0);
            forward(1 ,-500 , 1);
            turn(1, -1000, 1);
            forward(0.5, 4000, 10);
            strafeLeft(0.5, 750, 10, 50);
            set_all_motors_zero();
//
//        fLD.setPower(TURN_SPEED);
        }

        public void set_all_motors_zero() {
            fLD.setPower(0);
            fRD.setPower(0);
            bRD.setPower(0);
            bLD.setPower(0);
            pollen.setPower(0);
            nectar.setPower(0);
            intake.setPower(0);
//        todo intakeMotor.setPower(0);
//        todo servoRight.setPower(0);
//        todo servoLeft.setPower(0);

        }


        public void forward(double speed,
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
                fLD.setPower(0);
                fRD.setPower(0);
                bLD.setPower(0);
                bRD.setPower(0);
                sleep(250);


            }

        }


        public void strafeLeft(double speed,
                               double ticks,
                               double timeoutS,
                               double strafe) {
            int newTarget;
            if (opModeIsActive()) {
                newTarget = fLD.getCurrentPosition() + (int) (ticks);
                fLD.setTargetPosition(newTarget);
                newTarget = fRD.getCurrentPosition() - (int) (ticks);
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

                fLD.setPower(0);
                fRD.setPower(0);
                bLD.setPower(0);
                bRD.setPower(0);
                sleep(250);
            }
        }

        public void strafeRight(double speed,
                                double ticks,
                                double timeoutS,
                                double strafe) {
            int newTarget;
            if (opModeIsActive()) {
                newTarget = fLD.getCurrentPosition() - (int) (ticks);
                fLD.setTargetPosition(newTarget);
                newTarget = fRD.getCurrentPosition() + (int) (ticks);
                fRD.setTargetPosition(newTarget);
                newTarget = bLD.getCurrentPosition() + (int) (ticks);
                bLD.setTargetPosition(newTarget);
                newTarget = bRD.getCurrentPosition() - (int) (ticks);
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
                fLD.setPower(0);
                fRD.setPower(0);
                bLD.setPower(0);
                bRD.setPower(0);
                sleep(250);
            }
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


                fLD.setPower(0);
                fRD.setPower(0);
                bLD.setPower(0);
                bRD.setPower(0);
                sleep(250);
            }
        }
    }
}
