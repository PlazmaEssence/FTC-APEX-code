package org.firstinspires.ftc.teamcode;



import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//todo import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
//todo time shooter and encoders

@Autonomous(name="red_Duncan2", group="Robot")

public class encoderAutoRed2Duncan extends LinearOpMode {
    //    todo private DcMotor intakeMotor;
    //    todo private boolean toggle = false;
//    todo private boolean toggle2 = false;
//    todo private CRServo servoLeft;
//    todo private CRServo servoRight;
    private DcMotor fLD = null;
    private DcMotor fRD = null;
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
        fLD = hardwareMap.get(DcMotor.class, "FLD");
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
        strafeLeft(0.5, 1000, 10, 50);
        forward(0.5, 4000, 10);
        strafeLeft(0.5, 750, 10, 50);
        //todo strafeRight(0.5, 500, 10, 50);
        //todo turn(0.5, 500, 10);

        set_all_motors_zero();
//
//        fLD.setPower(TURN_SPEED);
//        fRD.setPower(-TURN_SPEED);
//        bRD.setPower(TURN_SPEED);
//        bLD.setPower(-TURN_SPEED);
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
        fLD.setPower(0);
        fRD.setPower(0);
        bRD.setPower(0);
        bLD.setPower(0);
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
    public void strafeRight ( double speed,
                              double ticks,
                              double timeoutS,
                              double strafe){
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
    public void turn ( double speed,
                       double ticks,
                       double timeoutS){
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
