package org.firstinspires.ftc.teamcode;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name="red_1", group="Robot")

public class encoderAutoRed1 extends LinearOpMode {
    boolean sense = true;
    boolean toggle;
    int count = 0;
    private Servo sorter1;
    private Servo sorter2;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private AnalogInput laserAnalog;
    private DcMotor pollen = null;
    private DcMotor nectar = null;
    private DcMotor intake = null;
    private ElapsedTime runtime = new ElapsedTime();

    private static final double MAX_VOLTS = 3.3;
    private static final double MAX_DISTANCE_MM = 1000.0;
    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;
    public void runOpMode() {
        pollen = hardwareMap.get(DcMotor.class, "pS");
        nectar = hardwareMap.get(DcMotor.class, "nS");
        intake = hardwareMap.get(DcMotor.class, "intake");
        sorter1 = hardwareMap.get(Servo.class, "sorterP");
        sorter2 = hardwareMap.get(Servo.class, "sorterN");
        laserAnalog = hardwareMap.get(AnalogInput.class, "laserAnalogInput");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fld");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frd");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bld");
        backRightDrive = hardwareMap.get(DcMotor.class, "brd");
//       todo intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
//        servoLeft = hardwareMap.get(CRServo.class, "sL");
//        servoRight = hardwareMap.get(CRServo.class, "sR");

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        pollen.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);
        telemetry.addData("Status", "Ready to run");
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

        sleep(10000);
        pollen.setPower(1);
        sleep(500);
        forward(0.5, 1000, 10);
        strafeRight(0.5, 2500, 10, 50);
        set_all_motors_zero();
    }

    public void set_all_motors_zero() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
    }


    public void forward(double speed,
                        double ticks,
                        double timeoutS) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = frontLeftDrive.getCurrentPosition() - (int) (ticks);
            frontLeftDrive.setTargetPosition(newTarget);
            newTarget = frontRightDrive.getCurrentPosition() - (int) (ticks);
            frontRightDrive.setTargetPosition(newTarget);
            newTarget = backLeftDrive.getCurrentPosition() - (int) (ticks);
            backLeftDrive.setTargetPosition(newTarget);
            newTarget = backRightDrive.getCurrentPosition() - (int) (ticks);
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
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", frontLeftDrive.getCurrentPosition());
                telemetry.update();


            }
            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);
            sleep(250);


        }

    }


    public void strafeLeft(double speed,
                           double ticks,
                           double timeoutS,
                           double strafe) {
        int newTarget;
        if (opModeIsActive()) {
            newTarget = frontLeftDrive.getCurrentPosition() + (int) (ticks);
            frontLeftDrive.setTargetPosition(newTarget);
            newTarget = frontRightDrive.getCurrentPosition() - (int) (ticks);
            frontRightDrive.setTargetPosition(newTarget);
            newTarget = backLeftDrive.getCurrentPosition() - (int) (ticks);
            backLeftDrive.setTargetPosition(newTarget);
            newTarget = backRightDrive.getCurrentPosition() + (int) (ticks);
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
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", frontLeftDrive.getCurrentPosition());
                telemetry.update();
            }
            set_all_motors_zero();

            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);
            sleep(250);
        }
        }
        public void strafeRight ( double speed,
        double ticks,
        double timeoutS,
        double strafe){
            int newTarget;
            if (opModeIsActive()) {
                newTarget = frontLeftDrive.getCurrentPosition() - (int) (ticks);
                frontLeftDrive.setTargetPosition(newTarget);
                newTarget = frontRightDrive.getCurrentPosition() + (int) (ticks);
                frontRightDrive.setTargetPosition(newTarget);
                newTarget = backLeftDrive.getCurrentPosition() + (int) (ticks);
                backLeftDrive.setTargetPosition(newTarget);
                newTarget = backRightDrive.getCurrentPosition() - (int) (ticks);
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
                    telemetry.addData("Running to", " %7d", newTarget);
                    telemetry.addData("Currently at ", frontLeftDrive.getCurrentPosition());
                    telemetry.update();
                }
                set_all_motors_zero();

                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                backLeftDrive.setPower(0);
                backRightDrive.setPower(0);
                sleep(250);
            }
        }
    public void turn ( double speed,
                       double ticks,
                       double timeoutS){
        int newTarget;
        if (opModeIsActive()) {
            newTarget = frontLeftDrive.getCurrentPosition() - (int) (ticks);
            frontLeftDrive.setTargetPosition(newTarget);
            newTarget = frontRightDrive.getCurrentPosition() + (int) (ticks);
            frontRightDrive.setTargetPosition(newTarget);
            newTarget = backLeftDrive.getCurrentPosition() - (int) (ticks);
            backLeftDrive.setTargetPosition(newTarget);
            newTarget = backRightDrive.getCurrentPosition() + (int) (ticks);
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
                telemetry.addData("Running to", " %7d", newTarget);
                telemetry.addData("Currently at ", frontLeftDrive.getCurrentPosition());
                telemetry.update();
            }
            set_all_motors_zero();

            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);
            sleep(250);
        }
        }
    }