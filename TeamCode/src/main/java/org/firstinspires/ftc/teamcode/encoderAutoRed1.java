package org.firstinspires.ftc.teamcode;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="red_1", group="Robot")

public class encoderAutoRed1 extends LinearOpMode {
    private Servo sorter1;
    private Servo sorter2;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor pollen = null;
    private DcMotor nectar = null;
    private DcMotor intake = null;
    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;
    public void runOpMode() {
        pollen = hardwareMap.get(DcMotor.class, "pollen");
        nectar = hardwareMap.get(DcMotor.class, "nectar");
        intake = hardwareMap.get(DcMotor.class, "intake");
        sorter1 = hardwareMap.get(Servo.class, "sorter1");
        sorter2 = hardwareMap.get(Servo.class, "sorter2");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "FLD");
        frontRightDrive = hardwareMap.get(DcMotor.class, "FRD");
        backLeftDrive = hardwareMap.get(DcMotor.class, "BLD");
        backRightDrive = hardwareMap.get(DcMotor.class, "BRD");
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        waitForStart();
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