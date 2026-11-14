package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
//todo 312 rmp motor is 538 ticks.
// 6000 rpm motor is 28 ticks.

//todo idk

    @TeleOp(name = "BiobuzzTeleopSebastian", group = "Robot")

    public class BiobuzzTeleopSebastian extends LinearOpMode {


        boolean sense = true;
        boolean toggle;


        private AnalogInput laserAnalog;
        private Servo intakeS1 = null;
        int count = 0;


        // Sensor scale: 3.3V corresponds to 1000 mm
        private static final double MAX_VOLTS = 3.3;
        private static final double MAX_DISTANCE_MM = 1000.0;

        double target_distance;
        private ElapsedTime runtime = new ElapsedTime();
        private DcMotor bLD = null;
        private DcMotor bRD = null;
        private DcMotor fLD = null;
        private DcMotor fRD = null;


        private DcMotor intake = null;





        private Servo pollenSorterServo = null;
        private Servo nectarSorterServo = null;
        double bLP;
        double bRP;
        double fLP;
        double fRP;

        boolean Toggle1 = false;
        boolean toggle2 = false;
        boolean toggle3 = false;
        boolean toggle4 = false;
        boolean toggle5 = false;
        double drive;
       double strafe;
      double  rotate;


        private DcMotor pollenShooter = null;
        private DcMotor nectarShooter = null;


        static final double FORWARD_SPEED = 0.5;
        static final double TURN_SPEED = 0.4;


        public void runOpMode() {
            telemetry.addData("Status", "Ready to run");    //
            telemetry.update();


            intake = hardwareMap.get(DcMotor.class, "intake");
            laserAnalog = hardwareMap.get(AnalogInput.class, "laserAnalogInput");
            intakeS1 = hardwareMap.get(Servo.class, "intakeS1");
            fLD = hardwareMap.get(DcMotor.class, "FLD");
            fRD = hardwareMap.get(DcMotor.class, "FRD");
            bLD = hardwareMap.get(DcMotor.class, "BLD");
            bRD = hardwareMap.get(DcMotor.class, "BRD");
            pollenShooter = hardwareMap.get(DcMotor.class, "pS");
            nectarShooter = hardwareMap.get(DcMotor.class, "nS");
            pollenSorterServo = hardwareMap.get(Servo.class, "sort1");
            nectarSorterServo = hardwareMap.get(Servo.class, "sort2");


            fLD.setDirection(DcMotor.Direction.FORWARD);
            fRD.setDirection(DcMotor.Direction.REVERSE);
            bLD.setDirection(DcMotor.Direction.FORWARD);
            bRD.setDirection(DcMotor.Direction.REVERSE);
            intake.setDirection(DcMotor.Direction.FORWARD);
            pollenShooter.setDirection(DcMotor.Direction.FORWARD);
            nectarShooter.setDirection(DcMotor.Direction.REVERSE);
            //pollenSorterServo.setDirection(DcMotorSimple.Direction.FORWARD);
            //nectarSorterServo.setDirection(DcMotorSimple.Direction.FORWARD);
            waitForStart();
            runtime.reset();


            // Wait for PLAY
            waitForStart();
            // Main loop

            while (opModeIsActive()) {




                drive = -gamepad1.left_stick_y;
                 strafe = gamepad1.left_stick_x;
                rotate = gamepad1.right_stick_x;






                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.addData("Motors", "front left (%.2f)," +
                                " front right (%.2f), back left (%.2f), back right (%.2f)",
                        fRP, fLP, bLP, bRP);
                telemetry.update();

                // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
                // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
                // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
                // Send telemetry message to signify robot waiting;

//todo check if it is 0 or 1
                pollenSorterServo.setPosition(0);
                nectarSorterServo.setPosition(0);

                // Read sensor voltage (0.0–3.3V)
                double volts = laserAnalog.getVoltage();
                // Convert voltage to distance in millimeters (linear mapping)
                double distanceMM = (volts / MAX_VOLTS) * MAX_DISTANCE_MM;
                if (distanceMM < 250)
                    telemetry.addData("count", "");


                if (distanceMM < 250) {
                    if (sense == true) {
                        count += 1;
                        sense = false;

                    }
                    if (sense == false) ;

                } else {
                    sense = true;

                }

                if (count == 4) {
                    intakeS1.setPosition(1);
                } else {
                    intakeS1.setPosition(0);
                }
                telemetry.addData("Voltage (V)", "%.3f", volts);
                telemetry.addData("Distance (mm)", "%.1f", distanceMM);
                telemetry.addData("Count", count);
                telemetry.update();


                if (gamepad1.aWasPressed()) {
                    intakemotor(0.8, 538, 1);

                } else {
                    set_intake_zero();

                }

                if (gamepad2.rightBumperWasPressed()) {
                    toggle2 = !toggle2;

                }
                if (toggle2) {// 4984
                    pollenShooter1(0.6, 2800, 3);


                } else {
                    set_pollenShooter_zero();


                }
                if (gamepad2.leftTriggerWasPressed()) {
                    toggle5 = !toggle5;
                }

                if (toggle5) {
                    pollenShooter.setPower(0.5);

                } else {

                    set_pollenShooter_zero();
                }

                if (gamepad2.rightTriggerWasPressed()) {
                    toggle3 = !toggle3;
                }

                if (toggle3) {
                    nectarShooter.setPower(0.5);

                } else {

                    nectarShooter.setPower(0);


                }

                if (gamepad2.leftBumperWasPressed()) {

                    toggle4 = !toggle4;
                }

                if (toggle4) {
                    necterShooter1(0.6, 28, 3);


                } else {

                    setNectarzero();
                }
            }
            if (gamepad1.right_trigger > 0.5) {
                slowmotor();

            }else{
                fLP = Range.clip(drive - strafe - rotate, -0.9, 0.9);
                fRP = Range.clip(drive + strafe + rotate, -0.9, 0.9);
                bLP = Range.clip(drive + strafe - rotate, -0.9, 0.9);
                bRP = Range.clip(drive - strafe + rotate, -0.9, 0.9);

                bLD.setPower(bLP);
                bRD.setPower(bRP);
                fLD.setPower(fLP);
                fRD.setPower(fRP);

            }
        }


// TODO       public void forward(double speed,
//                            double ticks,
//                            double timeoutS) {
//            int newTarget;
//            //DcMotor.RunMode runToPosition = RUN_TO_POSITION;
//            if (opModeIsActive()) {
//                newTarget = fLD.getCurrentPosition() - (int) (ticks);
//                fLD.setTargetPosition(newTarget);
//                newTarget = fRD.getCurrentPosition() - (int) (ticks);
//                fRD.setTargetPosition(newTarget);
//                newTarget = bLD.getCurrentPosition() - (int) (ticks);
//                bLD.setTargetPosition(newTarget);
//                newTarget = bRD.getCurrentPosition() - (int) (ticks);
//                bRD.setTargetPosition(newTarget);
//
//     todo           fLD.setPower(Math.abs(speed));
//                fRD.setPower(Math.abs(speed));
//                bLD.setPower(Math.abs(speed));
//                bRD.setPower(Math.abs(speed));
//
//
//      todo          fLD.setMode(RUN_TO_POSITION);
//                fRD.setMode(RUN_TO_POSITION);
//                bLD.setMode(RUN_TO_POSITION);
//                bRD.setMode(RUN_TO_POSITION);
//                runtime.reset();
//                while (opModeIsActive() &&
//                        (runtime.seconds() < timeoutS) &&
//                        (fLD.isBusy())) {
//                    telemetry.addData("Running to", " %7d", newTarget);
//                    telemetry.addData("Currently at ", fLD.getCurrentPosition());
//                    telemetry.update();
//
//
//          todo      }
//                fLD.setPower(0);
//                fRD.setPower(0);
//                bLD.setPower(0);
//                bRD.setPower(0);

      //      }
       // }//todo servopollen

        public void slowmotor() {

            fLP = Range.clip(drive - strafe - rotate, -0.5, 0.5);
            fRP = Range.clip(drive + strafe + rotate, -0.5, 0.5);
            bLP = Range.clip(drive + strafe - rotate, -0.5, 0.5);
            bRP = Range.clip(drive - strafe + rotate, -0.5, 0.5);

        }

        public void setNectarzero() {
            nectarShooter.setPower(0);
        }


        public void servonecter() {



        }


        public void set_all_motors_zero() {
            fLD.setPower(0);
            fRD.setPower(0);
            bRD.setPower(0);
            bLD.setPower(0);


        }

        public void set_intake_zero() {
            intake.setPower(0);
        }

        public void set_pollenShooter_zero() {
            pollenShooter.setPower(0);
        }

        public void pollenShooter1(double speed,

                                   double ticks,
                                   double timeoutS) {
            int newTarget;
            if (opModeIsActive()) {
                newTarget = pollenShooter.getCurrentPosition() - (int) (ticks);


                pollenShooter.setMode(RUN_TO_POSITION);
                runtime.reset();

                pollenShooter.setPower(Math.abs(speed));


                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (pollenShooter.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", pollenShooter.getCurrentPosition());
                    telemetry.update();

                }
                pollenSorterServo.setPosition(1);
                runtime.reset();


                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (pollenShooter.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", pollenShooter.getCurrentPosition());
                    telemetry.update();




                }


            }


            pollenSorterServo.setPosition(0);


            set_pollenShooter_zero();
        }


        public void necterShooter1(double speed,
                                   double ticks,
                                   double timeoutS) {
            if (opModeIsActive()) {
            int newTarget;
            //todo    if (opModeIsActive()) {
                newTarget = nectarShooter.getCurrentPosition() - (int) (ticks);


                nectarShooter.setMode(RUN_TO_POSITION);
                runtime.reset();

                nectarShooter.setPower(Math.abs(speed));


                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (nectarShooter.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", nectarShooter.getCurrentPosition());
                    telemetry.update();


                }
                nectarSorterServo.setPosition(1);
                runtime.reset();

                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (nectarShooter.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", nectarShooter.getCurrentPosition());
                    telemetry.update();




                }


            }


            nectarSorterServo.setPosition(0);


            set_pollenShooter_zero();
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



            }


        }

        public void intakemotor(double speed,
                                double ticks,
                                double timeoutS) {
            int newTarget;
            if (opModeIsActive()) {
                newTarget = intake.getCurrentPosition() + (int) (ticks);
                intake.setTargetPosition(newTarget);


                intake.setMode(RUN_TO_POSITION);
                runtime.reset();

                intake.setPower(Math.abs(speed));


                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (intake.isBusy())) {
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", intake.getCurrentPosition());
                    telemetry.update();
                }
             set_intake_zero();

            }
        }
    }
